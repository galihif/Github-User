package com.giftech.githubuser.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.giftech.githubuser.data.MainRepository
import com.giftech.githubuser.data.User

class FavouriteViewModel(private val mainRepository: MainRepository):ViewModel() {

    val loading = mainRepository.loading
    val error = mainRepository.error

    fun getAllFavUser():LiveData<List<User>> = mainRepository.getAllFavUser()

}