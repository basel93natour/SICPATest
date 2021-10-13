package com.basel.nytimesapp.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.basel.nytimesapp.data.models.search_articles.SearchArticleModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchArticlesDao {

    @Insert
    suspend fun insertArticle(articles : SearchArticleModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles : List<SearchArticleModel>)

    @Query("select * from search_articles")
    fun getAllArticles() : LiveData<List<SearchArticleModel>>

    @Query("select * from search_articles")
    fun getAllArticlesByKeyword() : PagingSource<Int, SearchArticleModel>


    @Query("DELETE FROM search_articles")
    suspend fun deleteAllArticles()
}