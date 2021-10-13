package com.basel.nytimesapp.data.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.basel.nytimesapp.data.remote.search_articles.SearchArticleModel

@Dao
interface SearchArticlesDao {

    @Insert
    suspend fun insertArticle(articles : SearchArticleModel)

    @Insert
    suspend fun insertArticles(articles : List<SearchArticleModel>)

    @Query("select * from search_articles")
    fun getAllArticles() : DataSource.Factory<Int, SearchArticleModel>
}