package com.basel.nytimesapp.network

import com.basel.nytimesapp.data.Constants.API_KEY
import com.basel.nytimesapp.data.models.popular_articles.PopularArticlesResponse
import com.basel.nytimesapp.data.models.search_articles.SearchArticleResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NYTimesApiService {

    @GET("search/v2/articlesearch.json")
    suspend fun getArticlesBySearch(
        @Query("q") searchQuery: String,
        @Query("api-key") apiKey: String = API_KEY,
        @Query("page") page: Int,
    ): SearchArticleResponse

    @GET("mostpopular/v2/{type}/7.json")
    suspend fun getMostViewedArticles(
        @Path("type") type : String,
        @Query("api-key") apiKey: String = API_KEY
    ): PopularArticlesResponse


}