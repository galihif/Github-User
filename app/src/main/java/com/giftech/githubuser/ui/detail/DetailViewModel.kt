package com.giftech.githubuser.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.giftech.githubuser.data.MainRepository
import com.giftech.githubuser.data.User

class DetailViewModel(private val mainRepository: MainRepository):ViewModel() {

    val loading = mainRepository.loading

    fun getDetailUser(username:String):LiveData<User> = mainRepository.getDetailUser(username)

}