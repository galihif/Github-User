package com.giftech.githubuser.utils

import com.giftech.githubuser.data.User
import com.giftech.githubuser.data.source.remote.response.GithubUser

object Mapper {

    fun mapListGithubUserToUser(list:List<GithubUser>):List<User>{
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

}