package com.duyha.github.ui.user.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.duyha.github.data.remote.model.Repo

class RepoAdapter(private var dataSet: List<Repo>) : RecyclerView.Adapter<RepoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepoViewHolder {
        return RepoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = dataSet.size

    fun refresh(dataSet: List<Repo>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}