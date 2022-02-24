package com.giftech.githubuser.di

import com.giftech.githubuser.data.MainRepository
import com.giftech.githubuser.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): MainRepository{
        val remoteDataSource = RemoteDataSource.getInstance()

        return MainRepository.getInstance(remoteDataSource)
    }
}