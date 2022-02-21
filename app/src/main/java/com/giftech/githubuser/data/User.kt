package com.giftech.githubuser.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name:String,
    var username:String,
    var avatar:String,
    var company:String="",
    var location:String="",
    var repository:Int=0,
    var following:Int=0,
    var followers:Int=0,
):Parcelable
