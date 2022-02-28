package com.giftech.githubuser.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SearchUserResponse(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? = null,

	@field:SerializedName("items")
	val items: List<GithubUser>? = null
){
	data class GithubUser(

		@field:SerializedName("login")
		val login: String? = null,

		@field:SerializedName("avatar_url")
		val avatarUrl: String? = null,

		@field:SerializedName("events_url")
		val eventsUrl: String? = null,
	)

}

