package com.basel.nytimesapp.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.basel.nytimesapp.data.remote.popular_articles.PopularArticlesModel

@Dao
interface PopularArticlesDao {

    @Insert
    suspend fun insertArticle(articles : PopularArticlesModel)

    @Insert
    suspend fun insertArticles(articles : List<PopularArticlesModel>)

    @Query("select * from popular_articles")
    fun getAllArticles() : LiveData<List<PopularArticlesModel>>
}