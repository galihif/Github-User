package com.giftech.githubuser.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.giftech.githubuser.data.MainRepository
import com.giftech.githubuser.data.User

class HomeViewModel(private val mainRepository: MainRepository):ViewModel() {

    val loading = mainRepository.loading
    val error = mainRepository.error

    fun getSearchedUser(keyword:String):LiveData<List<User>> = mainRepository.getSearchedUser(keyword)

}