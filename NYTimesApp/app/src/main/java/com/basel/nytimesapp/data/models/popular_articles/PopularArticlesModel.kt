package com.basel.nytimesapp.data.models.popular_articles

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_articles")
data class PopularArticlesModel(
    val title: String,
    @PrimaryKey
    val id: Long,
    val published_date: String,
    val articleType : String
)
{
    constructor() : this("",0L,"","")

}