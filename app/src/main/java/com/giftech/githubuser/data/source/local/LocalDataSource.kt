package com.giftech.githubuser.data.source.local

import com.giftech.githubuser.data.source.local.entity.FavUserEntity
import com.giftech.githubuser.data.source.local.preferences.SettingPreferences
import com.giftech.githubuser.data.source.local.room.FavUserDao

class LocalDataSource private constructor(
    private val favUserDao:FavUserDao,
    private val pref:SettingPreferences){


    fun getListFavUser():List<FavUserEntity> = favUserDao.getListFavUser()
    fun getFavUserByUsername(username:String):List<FavUserEntity> = favUserDao.getFavUserByUsername(username)
    fun insertFavUser(user:FavUserEntity) = favUserDao.insertFavUser(user)
    fun deleteFavUser(username:String) = favUserDao.deleteFavUser(username)

    fun setDarkTheme(isDark:Boolean) = pref.setDarkTheme(isDark)
    fun getIsDarkTheme():Boolean = pref.getIsDarkTheme()


    companion object{
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(favUserDao:FavUserDao, pref: SettingPreferences): LocalDataSource =
            INSTANCE ?: LocalDataSource(favUserDao, pref)
    }

}