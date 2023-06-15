package com.roman.poketest2.presentation.settings

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import com.roman.poketest2.R
import com.roman.poketest2.domain.ListFormat
import com.roman.poketest2.domain.Themes
import com.roman.poketest2.utils.LIST_FORMAT_SHARED_KEY
import com.roman.poketest2.utils.SHARED_PREFERENCES_NAME
import com.roman.poketest2.utils.THEME_SHARED_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun getThemeRadioButtonId(): Int {
        val themeName = sharedPreferences.getString(THEME_SHARED_KEY, "") ?: ""
        return when (Themes.fromString(themeName)) {
            Themes.DARK -> R.id.radio_button_dark
            Themes.LIGHT -> R.id.radio_button_light
            Themes.DEFAULT -> R.id.radio_button_default
        }
    }

    fun getListFormatRadioButtonId(): Int {
        val listFormatName = sharedPreferences.getString(LIST_FORMAT_SHARED_KEY, "") ?: ""
        return when (ListFormat.fromString(listFormatName)) {
            ListFormat.LIST -> R.id.radio_button_list
            ListFormat.GRID -> R.id.radio_button_grid
        }
    }

    fun setThemeByRadioButtonId(itemId: Int) {
        when (itemId) {
            R.id.radio_button_dark -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            R.id.radio_button_light -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            R.id.radio_button_default -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    fun saveThemeByRadioButtonId(itemId: Int) = sharedPreferences.edit {
        when (itemId) {
            R.id.radio_button_dark -> putString(THEME_SHARED_KEY, Themes.DARK.name)
            R.id.radio_button_default -> putString(THEME_SHARED_KEY, Themes.DEFAULT.name)
            R.id.radio_button_light -> putString(THEME_SHARED_KEY, Themes.LIGHT.name)
            else -> putString(THEME_SHARED_KEY, Themes.DEFAULT.name)
        }
        apply()
    }

    fun saveListFormatByRadioButtonId(itemId: Int) = sharedPreferences.edit() {
        when (itemId) {
            R.id.radio_button_list -> putString(LIST_FORMAT_SHARED_KEY, ListFormat.LIST.name)
            R.id.radio_button_grid -> putString(LIST_FORMAT_SHARED_KEY, ListFormat.GRID.name)
            else -> ListFormat.LIST
        }
        apply()
    }

    fun getListFormatNameByRadioButtonId(itemId: Int) = when (itemId) {
        R.id.radio_button_list -> ListFormat.LIST
        R.id.radio_button_grid -> ListFormat.GRID
        else -> ListFormat.LIST
    }.name


}