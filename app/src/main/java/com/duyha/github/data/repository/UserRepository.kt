package com.duyha.github.data.repository

import com.duyha.github.data.remote.GithubApi
import com.duyha.github.data.remote.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val githubApi: GithubApi
) {

    //TODO change this line to get user from githubApi
    suspend fun getUser(): User = User("", "", "", "", 0)
}