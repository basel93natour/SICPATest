package com.basel.nytimesapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.basel.nytimesapp.data.Constants
import com.basel.nytimesapp.data.models.popular_articles.PopularArticlesModel
import com.basel.nytimesapp.data.models.search_articles.SearchArticleModel
import com.basel.nytimesapp.network.Resource
import com.basel.nytimesapp.repo.DataRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val dataRepository: DataRepository) : ViewModel() {

    var searchKeyword :String?=""
    val mostViewedArticles = MutableLiveData<Resource<List<PopularArticlesModel>>>()

    fun getArticlesByKeyword(keyword : String?=null) : LiveData<PagingData<SearchArticleModel>> {
        if (!keyword.isNullOrEmpty())
            searchKeyword=keyword
        return dataRepository.getArticles(searchKeyword!!).cachedIn(viewModelScope)
    }


    fun searchSavedArticlesData(): LiveData<PagingData<SearchArticleModel>> {
        return dataRepository.getSavedArticles().cachedIn(viewModelScope)
    }

    fun getPopularArticles(articlesTypes: Constants.ArticlesTypes)
    {
        mostViewedArticles.value=Resource.Loading()
        viewModelScope.launch {
            dataRepository.getPopularArticles(articlesTypes).collect { response->
                when(response)
                {
                    is Resource.Success ->
                    {
                        mostViewedArticles.value=Resource.Success(response.data!!)
                    }
                    is Resource.Error ->
                    {
                        response.data?.let {
                            mostViewedArticles.value=Resource.Success(response.data!!)
                        }?: kotlin.run {
                            mostViewedArticles.value= response.error?.let { Resource.Error(it) }
                        }

                    }
                }
            }
        }

    }

}