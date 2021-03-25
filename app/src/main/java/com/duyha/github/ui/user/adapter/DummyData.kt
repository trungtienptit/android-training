package com.duyha.github.ui.user.adapter

import com.duyha.github.data.remote.model.Repo
import com.duyha.github.data.remote.model.User

object DummyData {
    val USER = User(
        avatarUrl = "https://avatars.githubusercontent.com/u/1342004?v=4",
        name = "Google",
        blog = "https://opensource.google/",
        email = "opensource@google.com",
        publicRepos = 1987
    )
    val REPO_LIST = (1..10).map { id ->
        Repo(
            id = id.toLong(),
            fullName = "Repo $id",
            description = "Description of repo number $id",
            language = "Kotlin, Java, Swift, Dart",
            htmlUrl = "",
            stargazersCount = 10,
            watchersCount = 9,
            forksCount = 8,
            updatedAt = "Mar 25, 2021"
        )
    }
}