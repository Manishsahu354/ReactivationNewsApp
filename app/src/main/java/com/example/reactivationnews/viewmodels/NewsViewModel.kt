package com.example.reactivationnews.viewmodels


import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.reactivationnews.data.model.ArticlesItem
import com.example.reactivationnews.data.model.NewsResponseDTO
import com.example.reactivationnews.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val newsData = newsRepository.getNews().cachedIn(viewModelScope)
    private val _savedNewsLiveData: MutableLiveData<MutableList<ArticlesItem>> = MutableLiveData()
    val saveNewsLiveData = _savedNewsLiveData

    fun bookmarksNews(articlesItem: ArticlesItem) {
        viewModelScope.launch {
            newsRepository.bookmarksNews(articlesItem)
        }
    }

    fun removeBookmarkNews(articlesItem: ArticlesItem) {
        viewModelScope.launch {
            newsRepository.removeBookmarkNews(articlesItem)
        }
    }

    fun getAllSavedNews(){
        viewModelScope.launch {
            val response = newsRepository.getAllSavedNews()
            _savedNewsLiveData.postValue(response)
        }
    }
}