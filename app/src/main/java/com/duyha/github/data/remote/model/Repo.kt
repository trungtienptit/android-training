package com.duyha.github.data.remote.model

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("id") val id : Long,
    @SerializedName("full_name") val fullName : String,
    @SerializedName("description") val description : String,
    @SerializedName("language") val language : String,
    @SerializedName("html_url") val htmlUrl : String,
    @SerializedName("updated_at") val updatedAt : String,
    @SerializedName("stargazers_count") val stargazersCount : Int,
    @SerializedName("watchers_count") val watchersCount : Int,
    @SerializedName("forks_count") val forksCount : Int
)