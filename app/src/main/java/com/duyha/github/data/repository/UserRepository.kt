package com.duyha.github.data.repository

import com.duyha.github.data.remote.GithubApi
import com.duyha.github.data.remote.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val githubApi: GithubApi
) {

    suspend fun getUser(): User = githubApi.getUser()
}