package com.basel.nytimesapp.data.models.search_articles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_articles")
data class SearchArticleModel(
    @PrimaryKey
    var _id: String,
    @ColumnInfo(name = "title")
    var `abstract`: String,
    var pub_date: String
)
{
    constructor() : this("","","")
}