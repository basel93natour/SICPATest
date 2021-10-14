package com.basel.nytimesapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.paging.*
import androidx.room.withTransaction
import com.basel.nytimesapp.data.Constants
import com.basel.nytimesapp.data.local.AppDatabase
import com.basel.nytimesapp.data.local.PopularArticlesDao
import com.basel.nytimesapp.data.local.SearchArticlesDao
import com.basel.nytimesapp.data.models.popular_articles.PopularArticlesModel
import com.basel.nytimesapp.data.models.search_articles.SearchArticleModel
import com.basel.nytimesapp.network.NYTimesApiService
import com.basel.nytimesapp.network.Resource
import com.basel.nytimesapp.network.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DataRepository {

    fun getArticles(keyword: String): LiveData<PagingData<SearchArticleModel>>
    fun getSavedArticles(): LiveData<PagingData<SearchArticleModel>>
    fun getPopularArticles(articlesTypes: Constants.ArticlesTypes):  Flow<Resource<List<PopularArticlesModel>>>

}