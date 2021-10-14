package com.basel.nytimesapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.basel.nytimesapp.data.Constants
import com.basel.nytimesapp.data.models.popular_articles.PopularArticlesModel
import com.basel.nytimesapp.data.models.search_articles.SearchArticleModel
import com.basel.nytimesapp.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeDataRepository : DataRepository {

    private val articlesPagingData= MutableLiveData(PagingData.from(listOf(SearchArticleModel( "1","",  ""),
        SearchArticleModel( "2","",  ""),
        SearchArticleModel( "3","",  ""))))

    private val popularArticlesData= flowOf(
        Resource.Success(listOf(PopularArticlesModel( "test",1,"",  Constants.ArticlesTypes.MOST_SHARED),
            PopularArticlesModel( "test",2,"",  Constants.ArticlesTypes.MOST_SHARED),
            PopularArticlesModel( "test",3,"",  Constants.ArticlesTypes.MOST_SHARED))))


    override fun getArticles(keyword: String): LiveData<PagingData<SearchArticleModel>> {
        return articlesPagingData
    }

    override fun getSavedArticles(): LiveData<PagingData<SearchArticleModel>> {
        return articlesPagingData
    }

    override fun getPopularArticles(articlesTypes: Constants.ArticlesTypes): Flow<Resource<List<PopularArticlesModel>>> {
        return popularArticlesData
    }

}