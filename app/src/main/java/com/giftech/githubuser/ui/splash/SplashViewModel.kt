package com.giftech.githubuser.ui.splash

import androidx.lifecycle.ViewModel
import com.giftech.githubuser.data.MainRepository

class SplashViewModel(private val mainRepository: MainRepository):ViewModel() {

    fun getIsDarkTheme():Boolean = mainRepository.getIsDarkTheme()

}