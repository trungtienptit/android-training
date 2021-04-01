package com.duyha.github.data.remote.model

import com.google.gson.annotations.SerializedName

//TODO map json to Repo object
data class Repo(
    @SerializedName("id") val id : Long,
    val fullName : String,
    val description : String,
    val language : String,
    val htmlUrl : String,
    val updatedAt : String,
    val stargazersCount : Int,
    val watchersCount : Int,
    val forksCount : Int
)