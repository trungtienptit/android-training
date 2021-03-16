package com.duyha.github.data.remote.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url") val avatarUrl : String,
    @SerializedName("name") val name : String,
    @SerializedName("blog") val blog : String,
    @SerializedName("email") val email : String,
    @SerializedName("public_repos") val publicRepos : Int
) {
    fun mapToDomain() = User(avatarUrl, name, blog, email, publicRepos)
}