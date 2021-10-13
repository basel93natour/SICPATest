package com.basel.nytimesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.basel.nytimesapp.R
import com.basel.nytimesapp.adapters.SearchArticlesAdapter
import com.basel.nytimesapp.network.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.page_indicators.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel:MainViewModel by viewModels()
    private val articlesListAdapter by lazy {
        SearchArticlesAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var layoutManager = LinearLayoutManager(this)
        articles_rv.layoutManager=layoutManager
        articles_rv.adapter= articlesListAdapter
        getArticles()

        lifecycleScope.launch {
            articlesListAdapter.loadStateFlow.collectLatest { loadStates ->
                if (loadStates.refresh is LoadState.Error)
                    getSavedArticles()
                cl_progress_bar.isVisible=loadStates.refresh is LoadState.Loading
            }
        }

        swiperefresh.setOnRefreshListener {
            articlesListAdapter.submitData(lifecycle, PagingData.empty())
            articlesListAdapter.notifyDataSetChanged()
            getArticles()
            swiperefresh.isRefreshing = false
        }
    }

    private fun getArticles()
    {
        viewModel.getArticlesByKeyword("").observe(this, Observer {
            lifecycleScope.launch {
                articlesListAdapter.submitData(it)
                articlesListAdapter.notifyDataSetChanged()

            }
        })
    }

    private fun getSavedArticles()
    {
        viewModel.searchSavedArticlesData("").observe(this, {

            lifecycleScope.launch {
                it?.let {
                    articlesListAdapter.submitData(it)
                    articlesListAdapter.notifyDataSetChanged()

                }
            }
        })
    }
}