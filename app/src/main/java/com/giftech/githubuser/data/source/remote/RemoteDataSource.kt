package com.giftech.githubuser.data.source.remote

import com.giftech.githubuser.data.source.remote.network.ApiConfig
import com.giftech.githubuser.data.source.remote.response.DetailUserResponse
import com.giftech.githubuser.data.source.remote.response.SearchUserResponse
import com.giftech.githubuser.data.source.remote.response.UserFollowResponse
import retrofit2.Call
import retrofit2.Response

class RemoteDataSource {

    fun getSearchedUser(keyword:String, callback:GetSearchedUserCallback){
        val client = ApiConfig.getApiService().getSearchedUser(keyword)
        client.enqueue(object : retrofit2.Callback<SearchUserResponse>{
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>
            ) {
                if(response.isSuccessful){
                    val res = response.body()?.items
                    callback.onResponseReceived(res!!)
                }
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }

    fun getDetailuser(username:String, callback:GetDetailUserCallback){
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : retrofit2.Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if(response.isSuccessful){
                    val res = response.body()
                    callback.onResponseReceived(res!!)
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }

    fun getUserFollowersList(username: String, callback: GetUserFollowersListCallback){
        val client = ApiConfig.getApiService().getUserFollowersList(username)
        client.enqueue(object : retrofit2.Callback<List<UserFollowResponse>>{
            override fun onResponse(
                call: Call<List<UserFollowResponse>>,
                response: Response<List<UserFollowResponse>>
            ) {
                if(response.isSuccessful){
                    val res = response.body()
                    callback.onResponseReceived(res!!)
                }
            }

            override fun onFailure(call: Call<List<UserFollowResponse>>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }

    fun getUserFollowingList(username: String, callback: GetUserFollowingListCallback){
        val client = ApiConfig.getApiService().getUserFollowingList(username)
        client.enqueue(object : retrofit2.Callback<List<UserFollowResponse>>{
            override fun onResponse(
                call: Call<List<UserFollowResponse>>,
                response: Response<List<UserFollowResponse>>
            ) {
                if(response.isSuccessful){
                    val res = response.body()
                    callback.onResponseReceived(res!!)
                }
            }

            override fun onFailure(call: Call<List<UserFollowResponse>>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }


    interface GetSearchedUserCallback{
        fun onResponseReceived(res:List<SearchUserResponse.GithubUser>)
    }

    interface GetDetailUserCallback{
        fun onResponseReceived(res:DetailUserResponse)
    }

    interface GetUserFollowersListCallback{
        fun onResponseReceived(res:List<UserFollowResponse>)
    }

    interface GetUserFollowingListCallback{
        fun onResponseReceived(res:List<UserFollowResponse>)
    }

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }
}