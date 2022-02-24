package com.giftech.githubuser.data.source.remote.network

import com.giftech.githubuser.data.source.remote.response.SearchUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/search/users")
    fun getSearchedUser(
        @Query("q") keyword:String
    ): Call<SearchUserResponse>

}