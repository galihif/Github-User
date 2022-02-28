package com.giftech.githubuser.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(

	@field:SerializedName("bio")
	val bio: String,

	@field:SerializedName("login")
	val login: String,


	@field:SerializedName("company")
	val company: String?,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("public_repos")
	val publicRepos: Int,

	@field:SerializedName("gravatar_id")
	val gravatarId: String,


	@field:SerializedName("followers_url")
	val followersUrl: String,


	@field:SerializedName("url")
	val url: String,


	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,


	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("name")
	val name: String?,

	@field:SerializedName("location")
	val location: String?,

)
