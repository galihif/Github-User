package com.giftech.githubuser.ui.settings

import androidx.lifecycle.ViewModel
import com.giftech.githubuser.data.MainRepository

class SettingsViewModel(private val mainRepository: MainRepository):ViewModel() {

    fun getIsDarkTheme():Boolean = mainRepository.getIsDarkTheme()

    fun setDarkTheme(isDark:Boolean) = mainRepository.setDarkTheme(isDark)

}