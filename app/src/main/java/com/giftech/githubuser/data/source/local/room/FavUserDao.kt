package com.giftech.githubuser.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.giftech.githubuser.data.source.local.entity.FavUserEntity

@Dao
interface FavUserDao {

    @Query("SELECT * FROM favuserentities")
    fun getListFavUser():LiveData<List<FavUserEntity>>

    @Transaction
    @Query("SELECT * FROM favuserentities WHERE username = :username")
    fun getFavUserByUsername(username:String):LiveData<FavUserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavUser(favUserEntity: FavUserEntity)

    @Query("DELETE FROM favuserentities WHERE username = :username")
    fun deleteFavUser(username: String)

}