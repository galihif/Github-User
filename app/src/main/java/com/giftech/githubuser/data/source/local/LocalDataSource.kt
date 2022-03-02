package com.giftech.githubuser.data.source.local

import androidx.lifecycle.LiveData
import com.giftech.githubuser.data.source.local.entity.FavUserEntity
import com.giftech.githubuser.data.source.local.room.FavUserDao

class LocalDataSource private constructor(private val favUserDao:FavUserDao){


    fun getListFavUser():LiveData<List<FavUserEntity>> = favUserDao.getListFavUser()
    fun getFavUserByUsername(username:String):List<FavUserEntity> = favUserDao.getFavUserByUsername(username)
    fun insertFavUser(user:FavUserEntity) = favUserDao.insertFavUser(user)
    fun deleteFavUser(username:String) = favUserDao.deleteFavUser(username)

    companion object{
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(favUserDao:FavUserDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(favUserDao)
    }

}