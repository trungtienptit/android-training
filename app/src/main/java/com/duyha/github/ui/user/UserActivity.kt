package com.duyha.github.ui.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.duyha.github.ui.base.BaseActivity
import com.duyha.github.R
import com.duyha.github.data.remote.model.Repo
import com.duyha.github.data.remote.model.User
import com.duyha.github.databinding.ActivityUserBinding
import com.duyha.github.ui.user.adapter.DummyData
import com.duyha.github.ui.user.adapter.LoadStateAdapter
import com.duyha.github.ui.user.adapter.RepoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private val adapter = RepoAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    private fun setUp() {
        initRepoRecyclerView()
        initSwipeToRefresh()
        showDummyData()
    }

    private fun initRepoRecyclerView() {
        binding.repoList.adapter = adapter
    }

    private fun initSwipeToRefresh() {
        binding.swipeRefresh.setColorSchemeResources(R.color.colorAccent)
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun showDummyData() {
        showUser(DummyData.USER)
        showRepoList(DummyData.REPO_LIST)

    }

    private fun showUser(user: User) {
        //TODO(Show user info)
        /*Glide.with(this)
            .load(user.avatarUrl)
            .placeholder(R.drawable.avatar_placeholder)
            .into(binding.avatar)*/
    }

    private fun showRepoList(repoList: List<Repo>) {
        adapter.refresh(repoList)
    }


}
