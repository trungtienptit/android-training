package com.duyha.github.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.duyha.github.data.remote.GithubApi
import com.duyha.github.ui.base.BaseViewModel
import com.duyha.github.data.remote.model.User
import com.duyha.github.data.repository.RepoPagingSource
import com.duyha.github.data.repository.UserRepository
import com.duyha.github.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val githubApi: GithubApi
) : BaseViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
            get() = _user

    val repoFlow = Pager(
        PagingConfig(Constants.REPO_PAGE_SIZE)
    ) {
        RepoPagingSource(githubApi)
    }.flow
        .cachedIn(viewModelScope)

    fun getUser() {
        //TODO get user and then post to UI here
    }
}