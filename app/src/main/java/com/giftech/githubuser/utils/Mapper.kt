package com.giftech.githubuser.utils

import com.giftech.githubuser.data.User
import com.giftech.githubuser.data.source.remote.response.DetailUserResponse
import com.giftech.githubuser.data.source.remote.response.SearchUserResponse

object Mapper {

    fun mapListGithubUserToUser(list:List<SearchUserResponse.GithubUser>):List<User>{
        val listUser = arrayListOf<User>()
        list.forEach {
            val user = User(
                username = it.login.toString(),
                avatar = it.avatarUrl.toString()
            )
            listUser.add(user)
        }
        return listUser
    }

    fun mapDetailUserToUser(detailUser: DetailUserResponse): User {
        return User(
            name = detailUser.name,
            username = detailUser.login,
            avatar = detailUser.avatarUrl,
            company = detailUser.company ?: "Company Not Found",
            location = detailUser.location ?: "Location Not Found",
            repository = detailUser.publicRepos,
            following = detailUser.following,
            followers = detailUser.followers
        )
    }

}