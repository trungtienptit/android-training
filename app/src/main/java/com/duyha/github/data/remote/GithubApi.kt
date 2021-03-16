package com.duyha.github.data.remote

import com.duyha.github.data.remote.model.Repo
import com.duyha.github.data.remote.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("users/google")
    suspend fun getUser(): User

    @GET("users/google/repos")
    suspend fun getReposPage(@Query("per_page") perPage: Int,
                             @Query("page") page: Int) : List<Repo>
}