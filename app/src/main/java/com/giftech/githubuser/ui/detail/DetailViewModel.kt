package com.giftech.githubuser.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.giftech.githubuser.data.MainRepository
import com.giftech.githubuser.data.User

class DetailViewModel(private val mainRepository: MainRepository):ViewModel() {

    val loading = mainRepository.loading
    val error = mainRepository.error

    fun getDetailUser(username:String):LiveData<User> = mainRepository.getDetailUser(username)

    fun getUserFollowers(username:String):LiveData<List<User>> = mainRepository.getUserFollowersList(username)

    fun getUserFollowing(username:String):LiveData<List<User>> = mainRepository.getUserFollowingList(username)

    fun insertFavUser(user:User) = mainRepository.insertFavUser(user)

}