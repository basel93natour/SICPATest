package com.basel.nytimesapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.paging.toLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.basel.nytimesapp.data.Constants
import com.basel.nytimesapp.data.models.popular_articles.PopularArticlesModel
import com.basel.nytimesapp.data.models.search_articles.SearchArticleModel
import com.basel.nytimesapp.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class PopularArticlesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: PopularArticlesDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.popularArticlesDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun testInsertMostEmailedArticleIntoDB() = runBlockingTest{
        val article= PopularArticlesModel( "test",0,"",  Constants.ArticlesTypes.MOST_EMAILED)
        dao.insertArticle(article)
        val data=dao.getAllArticlesByType(Constants.ArticlesTypes.MOST_EMAILED).asLiveData().getOrAwaitValue()
        assertThat(data).contains(article)
    }

    @Test
    fun testInsertMostViewedArticleIntoDB() = runBlockingTest{
        val article= PopularArticlesModel( "test",0,"",  Constants.ArticlesTypes.MOST_VIEWED)
        dao.insertArticle(article)
        val data=dao.getAllArticlesByType(Constants.ArticlesTypes.MOST_VIEWED).asLiveData().getOrAwaitValue()
        assertThat(data).contains(article)
    }

    @Test
    fun testInsertMostSharedArticleIntoDB() = runBlockingTest{
        val article= PopularArticlesModel( "test",0,"",  Constants.ArticlesTypes.MOST_SHARED)
        dao.insertArticle(article)
        val data=dao.getAllArticlesByType(Constants.ArticlesTypes.MOST_SHARED).asLiveData().getOrAwaitValue()
        assertThat(data).contains(article)
    }

    @Test
    fun testDeleteAllRecords()  = runBlockingTest{
            dao.deleteAllArticles()
        val data=dao.getAllArticles().asLiveData().getOrAwaitValue()
        assertThat(data).isEmpty()
    }




}