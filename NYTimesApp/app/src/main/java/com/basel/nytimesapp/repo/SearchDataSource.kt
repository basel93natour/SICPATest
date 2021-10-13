package com.basel.nytimesapp.repo

import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.paging.PagingSource
import androidx.paging.toLiveData
import androidx.room.withTransaction
import com.basel.nytimesapp.data.Constants.STARTING_PAGE_INDEX
import com.basel.nytimesapp.data.local.AppDatabase
import com.basel.nytimesapp.data.local.PopularArticlesDao
import com.basel.nytimesapp.data.local.SearchArticlesDao
import com.basel.nytimesapp.data.models.search_articles.SearchArticleModel
import com.basel.nytimesapp.network.NYTimesApiService
import com.basel.nytimesapp.network.Resource
import com.basel.nytimesapp.network.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.NullPointerException
import javax.inject.Inject

class SearchDataSource constructor(
    private val keyword: String,
    private val nyTimesApiService: NYTimesApiService,
    private val searchArticlesDao: SearchArticlesDao
) : PagingSource<Int, SearchArticleModel>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchArticleModel> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = nyTimesApiService.getArticlesBySearch(
                searchQuery = keyword,
                page = page
            ).response.searchArticleModels

            searchArticlesDao.insertArticles(response)
            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = page + 1
            )


        } catch (exception: IOException) {
            val error = IOException("Something went wrong our team working on it")
            LoadResult.Error(error)

        } catch (exception: HttpException) {
            val error = IOException("Something went wrong our team working on it")
            LoadResult.Error(error)

        }


    }
}