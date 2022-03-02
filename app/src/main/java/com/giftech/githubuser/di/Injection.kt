package com.giftech.githubuser.di

import android.content.Context
import com.giftech.githubuser.data.MainRepository
import com.giftech.githubuser.data.source.local.LocalDataSource
import com.giftech.githubuser.data.source.local.room.FavUserDatabase
import com.giftech.githubuser.data.source.remote.RemoteDataSource
import com.giftech.githubuser.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MainRepository{
        val favUserDatabase = FavUserDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(favUserDatabase.favUserDao())
        val appExecutors = AppExecutors()

        return MainRepository.getInstance(remoteDataSource, localDataSource, appExecutors)

    }
}