package com.basel.nytimesapp.data.remote.search_articles

data class SearchArticleResponse(
    val copyright: String,
    val response: Response,
    val status: String
)