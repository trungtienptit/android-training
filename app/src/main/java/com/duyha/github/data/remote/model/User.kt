package com.duyha.github.data.remote.model

import com.google.gson.annotations.SerializedName

//TODO map json to User object
data class User(
    @SerializedName("avatar_url") val avatarUrl : String,
    val name : String,
    val blog : String,
    val email : String,
    val publicRepos : Int
)