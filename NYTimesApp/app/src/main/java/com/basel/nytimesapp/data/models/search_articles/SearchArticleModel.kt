package com.basel.nytimesapp.data.models.search_articles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "search_articles")
data class SearchArticleModel(
    @PrimaryKey
    var _id: String,
    @SerializedName("abstract")
    var title: String,
    var pub_date: String
)
{
    constructor() : this("","","")
}