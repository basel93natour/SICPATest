package com.basel.nytimesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.basel.nytimesapp.data.remote.popular_articles.PopularArticlesModel
import com.basel.nytimesapp.data.remote.search_articles.SearchArticleModel

@Database(entities = [PopularArticlesModel::class,SearchArticleModel::class],version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun searchArticlesDao() : SearchArticlesDao
    abstract fun popularArticlesDao() : PopularArticlesDao
}