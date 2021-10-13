package com.basel.nytimesapp.data.remote.popular_articles


data class PopularArticlesResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<PopularArticlesModel>,
    val status: String
)