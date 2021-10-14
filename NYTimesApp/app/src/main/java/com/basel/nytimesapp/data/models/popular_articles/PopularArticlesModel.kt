package com.basel.nytimesapp.data.models.popular_articles

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.basel.nytimesapp.data.Constants

@Entity(tableName = "popular_articles")
data class PopularArticlesModel(
    val title: String,
    @PrimaryKey
    val id: Long,
    val published_date: String,
    var articleType : Constants.ArticlesTypes
)
{
    constructor() : this("",0L,"", Constants.ArticlesTypes.DEFAULT)

}