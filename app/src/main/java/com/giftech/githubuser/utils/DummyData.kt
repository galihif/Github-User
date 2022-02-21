package com.giftech.githubuser.utils

import com.giftech.githubuser.data.User

object DummyData {

    fun getListUser():List<User>{
        return UsersData.listData
    }

}