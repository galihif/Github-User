package com.giftech.githubuser.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.giftech.githubuser.data.source.local.LocalDataSource
import com.giftech.githubuser.data.source.local.entity.FavUserEntity
import com.giftech.githubuser.data.source.remote.RemoteDataSource
import com.giftech.githubuser.data.source.remote.response.DetailUserResponse
import com.giftech.githubuser.data.source.remote.response.SearchUserResponse
import com.giftech.githubuser.data.source.remote.response.UserFollowResponse
import com.giftech.githubuser.utils.AppExecutors
import com.giftech.githubuser.utils.Mapper

class MainRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {

    private val _loading = MutableLiveData<Boolean>()
    val loading:LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error:LiveData<String> = _error

    fun getSearchedUser(keyword:String):LiveData<List<User>>{
        _loading.postValue(true)
        val listSearchedUser = MutableLiveData<List<User>>()
        remoteDataSource.getSearchedUser(keyword, object : RemoteDataSource.GetSearchedUserCallback{
            override fun onResponseReceived(res: List<SearchUserResponse.GithubUser>) {
                val listRes = Mapper.mapListGithubUserToUser(res)
                listSearchedUser.postValue(listRes)
                _loading.postValue(false)
            }

            override fun onErrorReceived(errorMessage: String) {
                _error.postValue(errorMessage)
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

            override fun onErrorReceived(errorMessage: String) {
                _error.postValue(errorMessage)
                _loading.postValue(false)
            }
        })
        return detailUser
    }

    fun getUserFollowersList(username: String):LiveData<List<User>>{
        val listFollowers = MutableLiveData<List<User>>()
        remoteDataSource.getUserFollowersList(username, object : RemoteDataSource.GetUserFollowersListCallback{
            override fun onResponseReceived(res: List<UserFollowResponse>) {
                val listRes = Mapper.mapListUserFollowersToUser(res)
                listFollowers.postValue(listRes)
            }

            override fun onErrorReceived(errorMessage: String) {
                _error.postValue(errorMessage)
                _loading.postValue(false)
            }
        })
        return listFollowers
    }

    fun getUserFollowingList(username: String):LiveData<List<User>>{
        val listFollowers = MutableLiveData<List<User>>()
        remoteDataSource.getUserFollowingList(username, object : RemoteDataSource.GetUserFollowingListCallback{
            override fun onResponseReceived(res: List<UserFollowResponse>) {
                val listRes = Mapper.mapListUserFollowersToUser(res)
                listFollowers.postValue(listRes)
            }

            override fun onErrorReceived(errorMessage: String) {
                _error.postValue(errorMessage)
                _loading.postValue(false)
            }
        })
        return listFollowers
    }

    fun getAllFavUser():LiveData<List<User>>{
        val listUser = MutableLiveData<List<User>>()
        appExecutors.diskIO().execute{
            val listFavUser = localDataSource.getListFavUser()
            listUser.postValue(Mapper.mapListFavUserEntityToListUser(listFavUser))
        }
        return listUser
    }

    fun checkFavUserByUsername(username: String):LiveData<List<FavUserEntity>>{
        val listFavUserByUsername = MutableLiveData<List<FavUserEntity>>()
        appExecutors.diskIO().execute{
            listFavUserByUsername.postValue(localDataSource.getFavUserByUsername(username))
        }
        return listFavUserByUsername
    }

    fun deleteFavUser(username: String){
        appExecutors.diskIO().execute{
            localDataSource.deleteFavUser(username)
        }
    }

    fun insertFavUser(user:User){
        appExecutors.diskIO().execute{
            localDataSource.insertFavUser(Mapper.mapUserToFavUserEntity(user))
        }
    }

    fun setDarkTheme(isDark:Boolean) = localDataSource.setDarkTheme(isDark)

    fun getIsDarkTheme():Boolean = localDataSource.getIsDarkTheme()

    companion object {
        @Volatile
        private var instance: MainRepository? = null
        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(remoteDataSource, localDataSource, appExecutors).apply { instance = this }
            }
    }

}