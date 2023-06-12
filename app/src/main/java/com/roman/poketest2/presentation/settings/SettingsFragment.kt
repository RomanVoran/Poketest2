package com.roman.poketest2.presentation.settings

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.roman.poketest2.R
import com.roman.poketest2.databinding.FragmentSettingsBinding
import com.roman.poketest2.domain.ListFormat
import com.roman.poketest2.domain.Themes
import dagger.hilt.android.AndroidEntryPoint
import com.roman.poketest2.presentation.main.CHANGE_LIST_FORMAT_REQUEST_KEY
import com.roman.poketest2.presentation.main.CLEAR_LIST_REQUEST_KEY


private const val LIST_FORMAT_KEY = "list_format"
private const val THEME_KEY = "theme"

@AndroidEntryPoint
class SettingsFragment : DialogFragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding get() = _binding!!
    private val sharedPrefs by lazy {
        requireActivity().getSharedPreferences(
            "settings",
            Context.MODE_PRIVATE
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListeners()

    }

    private fun initView() {
        dialog?.window?.setBackgroundDrawableResource(R.drawable.settings_background)

        sharedPrefs.getString(THEME_KEY, "")?.let { themeName ->
            binding.radioGroupTheme.check(
                when (Themes.fromString(themeName)) {
                    Themes.DARK -> R.id.radio_button_dark
                    Themes.LIGHT -> R.id.radio_button_light
                    Themes.DEFAULT -> R.id.radio_button_default
                }
            )
        }

        sharedPrefs.getString(LIST_FORMAT_KEY, "")?.let { listFormatName ->
            binding.radioGroupListFormat.check(
                when (ListFormat.fromString(listFormatName)) {
                    ListFormat.LIST -> R.id.radio_button_list
                    ListFormat.GRID -> R.id.radio_button_grid
                }
            )
        }


    }


    private fun initListeners() {
        binding.buttonClearList.setOnClickListener {
            setFragmentResult(CLEAR_LIST_REQUEST_KEY, bundleOf(Pair("", null)))
            dismiss()
        }

        binding.radioGroupTheme.setOnCheckedChangeListener { radioGroup, itemId ->
            when (itemId) {
                R.id.radio_button_dark -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                R.id.radio_button_light -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                R.id.radio_button_default -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
            saveTheme(itemId)
        }

        binding.radioGroupListFormat.setOnCheckedChangeListener { radioGroup, itemId ->
            saveListFormat(itemId)
        }
    }


    private fun saveTheme(itemId: Int) = sharedPrefs.edit {
        when (itemId) {
            R.id.radio_button_dark -> putString(THEME_KEY, Themes.DARK.name)
            R.id.radio_button_default -> putString(THEME_KEY, Themes.DEFAULT.name)
            R.id.radio_button_light -> putString(THEME_KEY, Themes.LIGHT.name)
        }
        apply()
        dismiss()
    }


    private fun saveListFormat(itemId: Int) = sharedPrefs.edit {
        val format = when (itemId) {
            R.id.radio_button_list -> ListFormat.LIST
            R.id.radio_button_grid -> ListFormat.GRID
            else -> ListFormat.LIST
        }
        apply()
        putString(LIST_FORMAT_KEY, format.name)
        setFragmentResult(
            CHANGE_LIST_FORMAT_REQUEST_KEY,
            bundleOf(Pair(CHANGE_LIST_FORMAT_REQUEST_KEY, format.name))
        )
        dismiss()
    }


}