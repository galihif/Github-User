package com.giftech.githubuser.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.giftech.githubuser.data.source.remote.RemoteDataSource
import com.giftech.githubuser.data.source.remote.response.GithubUser
import com.giftech.githubuser.utils.Mapper
import com.giftech.githubuser.utils.UsersData

class MainRepository(
    private val remoteDataSource: RemoteDataSource
) {

    private val _loading = MutableLiveData<Boolean>()
    val loading:LiveData<Boolean> = _loading


    fun getListUser():LiveData<List<User>>{
        _loading.postValue(true)
        val listUser = MutableLiveData<List<User>>()
        listUser.postValue(UsersData.listData)
        _loading.postValue(false)
        return  listUser
    }

    fun getSearchedUser(keyword:String):LiveData<List<User>>{
        _loading.postValue(true)
        val listSearchedUser = MutableLiveData<List<User>>()
        remoteDataSource.getSearchedUser(keyword, object : RemoteDataSource.GetSearchedUserCallback{
            override fun onResponseReceived(res: List<GithubUser>) {
                val listRes = Mapper.mapListGithubUserToUser(res)
                listSearchedUser.postValue(listRes)
                _loading.postValue(false)
            }
        })
        return listSearchedUser
    }

    companion object {
        @Volatile
        private var instance: MainRepository? = null
        fun getInstance(remoteDataSource: RemoteDataSource): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(remoteDataSource).apply { instance = this }
            }
    }

}