package com.basel.nytimesapp.data.remote.search_articles

data class Response(
    val searchArticleModels: List<SearchArticleModel>,
    val meta: Meta
)