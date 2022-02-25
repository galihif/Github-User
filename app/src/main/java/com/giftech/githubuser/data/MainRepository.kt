package com.giftech.githubuser.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.giftech.githubuser.data.source.remote.RemoteDataSource
import com.giftech.githubuser.data.source.remote.response.DetailUserResponse
import com.giftech.githubuser.data.source.remote.response.SearchUserResponse
import com.giftech.githubuser.data.source.remote.response.UserFollowResponse
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
            override fun onResponseReceived(res: List<SearchUserResponse.GithubUser>) {
                val listRes = Mapper.mapListGithubUserToUser(res)
                listSearchedUser.postValue(listRes)
                _loading.postValue(false)
            }
        })
        return listSearchedUser
    }

    fun getDetailUser(username:String):LiveData<User>{
        _loading.postValue(true)
        val detailUser = MutableLiveData<User>()
        remoteDataSource.getDetailuser(username, object :RemoteDataSource.GetDetailUserCallback{
            override fun onResponseReceived(res: DetailUserResponse) {
                val userRes = Mapper.mapDetailUserToUser(res)
                detailUser.postValue(userRes)
                _loading.postValue(false)
            }
        })
        return detailUser
    }

    fun getUserFollowersList(username: String):LiveData<List<User>>{
        _loading.postValue(true)
        val listFollowers = MutableLiveData<List<User>>()
        remoteDataSource.getUserFollowersList(username, object : RemoteDataSource.GetUserFollowersListCallback{
            override fun onResponseReceived(res: List<UserFollowResponse>) {
                val listRes = Mapper.mapListUserFollowersToUser(res)
                listFollowers.postValue(listRes)
                _loading.postValue(false)
            }
        })
        return listFollowers
    }

    fun getUserFollowingList(username: String):LiveData<List<User>>{
        _loading.postValue(true)
        val listFollowers = MutableLiveData<List<User>>()
        remoteDataSource.getUserFollowingList(username, object : RemoteDataSource.GetUserFollowingListCallback{
            override fun onResponseReceived(res: List<UserFollowResponse>) {
                val listRes = Mapper.mapListUserFollowersToUser(res)
                listFollowers.postValue(listRes)
                _loading.postValue(false)
            }
        })
        return listFollowers
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