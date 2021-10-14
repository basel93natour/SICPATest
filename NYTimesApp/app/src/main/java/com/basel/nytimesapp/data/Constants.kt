package com.basel.nytimesapp.data

object Constants {

    val API_KEY="mrVW92L9PptXzWSNWfCcicY1e8vZo8im"
    val STARTING_PAGE_INDEX=0

    enum class ArticlesTypes(val type : String){
        DEFAULT(""),
        MOST_VIEWED("viewed"),
        MOST_SHARED("shared"),
        MOST_EMAILED("emailed")
    }
}