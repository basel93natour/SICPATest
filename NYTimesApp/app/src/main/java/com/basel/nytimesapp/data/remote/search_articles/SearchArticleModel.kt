package com.basel.nytimesapp.data.remote.search_articles

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "search_articles")
data class SearchArticleModel(
    @PrimaryKey
    var _id: String,
    var `abstract`: String,
    var pub_date: String
)
{
    constructor() : this("","","")
}