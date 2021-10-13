package com.basel.nytimesapp.data.models.popular_articles


data class PopularArticlesResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<PopularArticlesModel>,
    val status: String
)