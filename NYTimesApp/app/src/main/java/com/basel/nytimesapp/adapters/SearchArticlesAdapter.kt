package com.basel.nytimesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.basel.nytimesapp.R
import com.basel.nytimesapp.data.models.search_articles.SearchArticleModel

class SearchArticlesAdapter : PagingDataAdapter<SearchArticleModel, SearchArticlesAdapter.ViewHolder>(ArticlesDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text=getItem(position)?.abstract
        holder.publishDate.text=getItem(position)?.pub_date
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
        return super.getItemCount()
    }

    private class ArticlesDiffCallback : DiffUtil.ItemCallback<SearchArticleModel>() {
        override fun areItemsTheSame(oldItem: SearchArticleModel, newItem: SearchArticleModel): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: SearchArticleModel, newItem: SearchArticleModel): Boolean {
            return oldItem == newItem
        }
    }
}