package com.duyha.github.ui.user

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.duyha.github.ui.base.BaseActivity
import com.duyha.github.R
import com.duyha.github.data.remote.model.User
import com.duyha.github.databinding.ActivityUserBinding
import com.duyha.github.ui.user.adapter.LoadStateAdapter
import com.duyha.github.ui.user.adapter.RepoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserActivity : BaseActivity<UserViewModel>() {

    private lateinit var binding: ActivityUserBinding
    private val adapter = RepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setUp() {
        initRepoRecyclerView()
        initSwipeToRefresh()
    }

    private fun initRepoRecyclerView() {
        binding.repoList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter(adapter::retry),
            footer = LoadStateAdapter(adapter::retry)
        )
    }

    private fun initSwipeToRefresh() {
        binding.swipeRefresh.setColorSchemeResources(R.color.colorAccent)
        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
    }

    override fun callViewModel() {
        viewModel.getUser()
    }

    override fun setObservers() {
        viewModel.user.observe(this) {
            showUser(it)
        }
        lifecycleScope.launch {
            viewModel.repoFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun showUser(user: User) {
        Glide.with(this)
            .load(user.avatarUrl)
            .placeholder(R.drawable.avatar_placeholder)
            .into(binding.avatar)
        binding.name.text = user.name
        binding.repoCount.text = getString(R.string.text_repositories, user.publicRepos)
        binding.blog.text = user.blog
        binding.email.text = user.email
    }

    override fun createViewModel(): UserViewModel =
        ViewModelProvider(this).get(UserViewModel::class.java)
}
