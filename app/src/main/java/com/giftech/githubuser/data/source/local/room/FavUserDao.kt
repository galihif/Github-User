package com.giftech.githubuser.data.source.local.room

import androidx.room.*
import com.giftech.githubuser.data.source.local.entity.FavUserEntity

@Dao
interface FavUserDao {

    @Query("SELECT * FROM favuserentities")
    fun getListFavUser():List<FavUserEntity>

    @Transaction
    @Query("SELECT * FROM favuserentities WHERE username = :username")
    fun getFavUserByUsername(username:String):List<FavUserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavUser(favUserEntity: FavUserEntity)

    @Query("DELETE FROM favuserentities WHERE username = :username")
    fun deleteFavUser(username: String)

}