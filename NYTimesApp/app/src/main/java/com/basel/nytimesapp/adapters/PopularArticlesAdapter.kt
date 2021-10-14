package com.basel.nytimesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.basel.nytimesapp.R
import com.basel.nytimesapp.data.models.popular_articles.PopularArticlesModel
import com.basel.nytimesapp.data.models.search_articles.SearchArticleModel

class PopularArticlesAdapter(private val articles : List<PopularArticlesModel>) : RecyclerView.Adapter<PopularArticlesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text=articles[position].title
        holder.publishDate.text=articles[position].published_date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false)
        return ViewHolder(v)

    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)
    {
        var title: TextView =itemView.findViewById(R.id.title)
        var publishDate: TextView =itemView.findViewById(R.id.publishDate)

    }

    override fun getItemCount(): Int {
        return articles.size
    }

}