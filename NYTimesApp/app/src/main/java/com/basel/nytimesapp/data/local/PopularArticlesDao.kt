package com.basel.nytimesapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.basel.nytimesapp.data.Constants
import com.basel.nytimesapp.data.models.popular_articles.PopularArticlesModel
import com.basel.nytimesapp.data.models.search_articles.SearchArticleModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularArticlesDao {

    @Insert
    suspend fun insertArticle(articles : PopularArticlesModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles : List<PopularArticlesModel>)

    @Query("select * from popular_articles where articleType=:type")
    fun getAllArticles(type : Constants.ArticlesTypes) : Flow<List<PopularArticlesModel>>

    @Query("DELETE FROM popular_articles")
    suspend fun deleteAllArticles()
}