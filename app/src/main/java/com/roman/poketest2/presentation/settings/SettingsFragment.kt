package com.roman.poketest2.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.roman.poketest2.R
import com.roman.poketest2.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import com.roman.poketest2.utils.CHANGE_LIST_FORMAT_RESULT_REQUEST_KEY
import com.roman.poketest2.utils.CLEAR_LIST_RESULT_REQUEST_KEY


@AndroidEntryPoint
class SettingsFragment : DialogFragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding get() = _binding!!
    private val viewModel: SettingsViewModel by viewModels()


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
        binding.radioGroupTheme.check(viewModel.getThemeRadioButtonId())
        binding.radioGroupListFormat.check(viewModel.getListFormatRadioButtonId())
    }


    private fun initListeners() {
        binding.buttonClearList.setOnClickListener {
            setFragmentResult(CLEAR_LIST_RESULT_REQUEST_KEY, bundleOf(Pair("", null)))
            dismiss()
        }

        binding.radioGroupTheme.setOnCheckedChangeListener { radioGroup, itemId ->
            viewModel.setThemeByRadioButtonId(itemId)
            viewModel.saveThemeByRadioButtonId(itemId)
            dismiss()
        }

        binding.radioGroupListFormat.setOnCheckedChangeListener { radioGroup, itemId ->
            viewModel.saveListFormatByRadioButtonId(itemId)
            setFragmentResult(
                CHANGE_LIST_FORMAT_RESULT_REQUEST_KEY,
                bundleOf(
                    Pair(
                        CHANGE_LIST_FORMAT_RESULT_REQUEST_KEY,
                        viewModel.getListFormatNameByRadioButtonId(itemId)
                    )
                )
            )
            dismiss()
        }
    }


}