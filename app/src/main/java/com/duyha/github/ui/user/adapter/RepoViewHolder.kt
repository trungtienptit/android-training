package com.duyha.github.ui.user.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duyha.github.R
import com.duyha.github.data.remote.model.Repo
import com.duyha.github.databinding.ItemNetworkStateBinding
import com.duyha.github.databinding.ItemRepoBinding
import com.duyha.github.utils.formatToString
import com.duyha.github.utils.toDateTime

class RepoViewHolder(parent: ViewGroup)
    : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
) {

    private val biding = ItemRepoBinding.bind(itemView)

    fun bind(item: Repo?) {
        item?.let { it ->
            biding.title.text = it.fullName
            biding.desc.text = it.description
            biding.updateTime.text = it.updatedAt
            biding.language.text = it.language
            biding.watcher.text = it.watchersCount.toString()
            biding.star.text = it.stargazersCount.toString()
            biding.fork.text = it.forksCount.toString()
        }
    }
}
