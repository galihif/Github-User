package com.giftech.githubuser.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favuserentities")
data class FavUserEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    var username:String="",

    @ColumnInfo(name = "avatar_url")
    var avatarUrl:String=""
):Parcelable
