package com.giftech.githubuser.utils

import com.giftech.githubuser.data.User
import com.giftech.githubuser.data.source.local.entity.FavUserEntity
import com.giftech.githubuser.data.source.remote.response.DetailUserResponse
import com.giftech.githubuser.data.source.remote.response.SearchUserResponse
import com.giftech.githubuser.data.source.remote.response.UserFollowResponse

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
            name = detailUser.name ?: "Name Not Found",
            username = detailUser.login,
            avatar = detailUser.avatarUrl,
            company = detailUser.company ?: "Company Not Found",
            location = detailUser.location ?: "Location Not Found",
            repository = detailUser.publicRepos,
            following = detailUser.following,
            followers = detailUser.followers
        )
    }

    fun mapListUserFollowersToUser(list:List<UserFollowResponse>):List<User>{
        val listUser = arrayListOf<User>()
        list.forEach {
            val user = User(
                username = it.login,
                avatar = it.avatarUrl
            )
            listUser.add(user)
        }
        return listUser
    }

    fun mapUserToFavUserEntity(user:User):FavUserEntity{
        return FavUserEntity(
            username = user.username,
            avatarUrl = user.avatar
        )
    }

}