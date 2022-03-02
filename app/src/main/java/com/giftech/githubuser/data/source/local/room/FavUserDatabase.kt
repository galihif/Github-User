package com.giftech.githubuser.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.giftech.githubuser.data.source.local.entity.FavUserEntity

@Database(entities = [FavUserEntity::class], version = 1, exportSchema = false)
abstract class FavUserDatabase:RoomDatabase() {

    abstract fun favUserDao():FavUserDao

    companion object{
        private const val DATABASE_NAME = "favuser.db"

        @Volatile
        private var INSTANCE: FavUserDatabase? = null

        fun getInstance(context: Context): FavUserDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    FavUserDatabase::class.java,
                    DATABASE_NAME
                ).build().apply {
                    INSTANCE = this
                }
            }
    }

}