package com.giftech.githubuser.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.giftech.githubuser.utils.DummyData

class MainRepository {

    fun getListUser():LiveData<List<User>>{
        val listUser = MutableLiveData<List<User>>()
        listUser.postValue(DummyData.getListUser())
        return  listUser
    }

    companion object {
        @Volatile
        private var instance: MainRepository? = null
        fun getInstance(): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository().apply { instance = this }
            }
    }

}