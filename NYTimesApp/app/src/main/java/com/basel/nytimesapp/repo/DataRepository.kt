package com.basel.nytimesapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.paging.*
import androidx.room.withTransaction
import com.basel.nytimesapp.data.local.AppDatabase
import com.basel.nytimesapp.data.local.PopularArticlesDao
import com.basel.nytimesapp.data.local.SearchArticlesDao
import com.basel.nytimesapp.data.models.search_articles.SearchArticleModel
import com.basel.nytimesapp.network.NYTimesApiService
import com.basel.nytimesapp.network.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class DataRepository @Inject constructor(private val nyTimesApiService: NYTimesApiService,
                                         private val appDatabase: AppDatabase,
                                         private val popularArticlesDao: PopularArticlesDao,
                                         private val searchArticlesDao: SearchArticlesDao) {



//    fun getArticleByKeyword(keyword: String) = networkBoundResource(
//            query = {
//                searchArticlesDao.getAllArticles()
//            },
//            fetch = {
//                nyTimesApiService.getArticlesBySearch(searchQuery = keyword,page = 0).response.searchArticleModels },
//            saveFetchResult = { articles ->
//                appDatabase.withTransaction {
//                    searchArticlesDao.deleteAllArticles()
//                   println("articles ${articles.size}")
//                    searchArticlesDao.insertArticles(articles)
//                }
//            }
//    )


    fun getArticles(keyword: String): LiveData<PagingData<SearchArticleModel>> {

        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 10),
            pagingSourceFactory = { SearchDataSource(keyword,nyTimesApiService,searchArticlesDao) }
        ).liveData
    }

    fun getSavedArticles(keyword: String) : LiveData<PagingData<SearchArticleModel>>{
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 10),
            pagingSourceFactory = { searchArticlesDao.getAllArticlesByKeyword(keyword) }
        ).liveData
    }
}