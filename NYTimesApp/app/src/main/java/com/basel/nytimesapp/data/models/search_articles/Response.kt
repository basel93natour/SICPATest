package com.basel.nytimesapp.data.models.search_articles

import com.google.gson.annotations.SerializedName

data class Response(
        @SerializedName("docs")
        val searchArticleModels: List<SearchArticleModel>,
    val meta: Meta
)