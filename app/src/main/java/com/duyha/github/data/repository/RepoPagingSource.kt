package com.duyha.github.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.duyha.github.data.remote.GithubApi
import com.duyha.github.data.remote.model.Repo
import com.duyha.github.utils.Constants
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepoPagingSource @Inject constructor(
    private val githubApi: GithubApi,
) : PagingSource<Int, Repo>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: 0
            val response = githubApi.getReposPage(Constants.REPO_PAGE_SIZE, page)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}