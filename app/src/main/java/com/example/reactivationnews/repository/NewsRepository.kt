package com.example.reactivationnews.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.reactivationnews.data.local.NewsDao
import com.example.reactivationnews.data.model.ArticlesItem
import com.example.reactivationnews.data.network.NewsApi
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) {

//    suspend fun getNews() = newsApi.getBreakingNews("in")
    fun getNews() =  Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { NewsPagingSource(newsApi) }
    ).liveData

    suspend fun bookmarksNews(articlesItem: ArticlesItem) = newsDao.insertData(articlesItem)
    suspend fun removeBookmarkNews(articlesItem: ArticlesItem) = newsDao.removeSavedNews(articlesItem)

    suspend fun getAllSavedNews() = newsDao.getAllSavedNews()
}