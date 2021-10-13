package com.basel.nytimesapp.data.models.search_articles

data class SearchArticleResponse(
    val copyright: String,
    val response: Response,
    val status: String
)