package com.giftech.githubuser.data.source.remote.network

import com.giftech.githubuser.BuildConfig
import com.giftech.githubuser.data.source.remote.response.DetailUserResponse
import com.giftech.githubuser.data.source.remote.response.SearchUserResponse
import com.giftech.githubuser.data.source.remote.response.UserFollowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/search/users")
    fun getSearchedUser(
        @Query("q") keyword:String
    ): Call<SearchUserResponse>

    @GET("/users/{username}")
    fun getDetailUser(
        @Path("username") username:String,
        @Header("Authorization") token: String = BuildConfig.API_KEY
    ): Call<DetailUserResponse>

    @GET("/users/{username}/followers")
    fun getUserFollowersList(
        @Path("username") username:String,
    ): Call<List<UserFollowResponse>>

    @GET("/users/{username}/following")
    fun getUserFollowingList(
        @Path("username") username:String,
    ): Call<List<UserFollowResponse>>



}