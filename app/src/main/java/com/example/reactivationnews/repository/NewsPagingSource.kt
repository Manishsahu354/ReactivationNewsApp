package com.example.reactivationnews.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.reactivationnews.data.model.ArticlesItem
import com.example.reactivationnews.data.network.NewsApi
import com.example.reactivationnews.utils.Constants.NEWS_STARTING_PAGE_INDEX
import java.io.IOException

class NewsPagingSource(
    private val newsApi: NewsApi,
):PagingSource<Int, ArticlesItem>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {
        val position = params.key ?: NEWS_STARTING_PAGE_INDEX

        return try {
            val response = newsApi.getBreakingNews("in", position, params.loadSize)
            val news = response.articles

            LoadResult.Page(
                data = news,
                prevKey = if (position == NEWS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (news.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}