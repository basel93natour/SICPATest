package com.basel.nytimesapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.basel.nytimesapp.data.models.search_articles.SearchArticleModel
import com.basel.nytimesapp.network.Resource
import com.basel.nytimesapp.repo.DataRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val dataRepository: DataRepository) : ViewModel() {

    val searchResponse = MutableLiveData<Resource<List<SearchArticleModel>>>()


    fun getArticlesData(): LiveData<PagingData<SearchArticleModel>> {
        return dataRepository.getArticles("election").cachedIn(viewModelScope)
    }

    fun getSavedArticlesData(): LiveData<PagingData<SearchArticleModel>> {
        return dataRepository.getSavedArticles().cachedIn(viewModelScope)
    }


}