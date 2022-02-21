package com.giftech.githubuser.di

import com.giftech.githubuser.data.MainRepository

object Injection {
    fun provideRepository(): MainRepository = MainRepository.getInstance()
}