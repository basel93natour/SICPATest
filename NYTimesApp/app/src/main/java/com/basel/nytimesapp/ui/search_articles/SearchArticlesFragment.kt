package com.basel.nytimesapp.ui.search_articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.basel.nytimesapp.R
import com.basel.nytimesapp.adapters.SearchArticlesAdapter
import com.basel.nytimesapp.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.articles_fragment.*
import kotlinx.android.synthetic.main.page_indicators.*
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchArticlesFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private val articlesListAdapter by lazy {
        SearchArticlesAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.articles_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var layoutManager = LinearLayoutManager(requireContext())
        rv_articles.layoutManager=layoutManager
        rv_articles.adapter= articlesListAdapter

        lifecycleScope.launch {
            articlesListAdapter.loadStateFlow.collectLatest { loadStates ->
                if (loadStates.refresh is LoadState.Error)
                    getSavedArticles()
                cl_progress_bar.isVisible = loadStates.refresh is LoadState.Loading
            }
        }

        getArticles()

        swiperefresh.setOnRefreshListener {
            articlesListAdapter.submitData(lifecycle, PagingData.empty())
            articlesListAdapter.notifyDataSetChanged()
            getArticles()
            swiperefresh.isRefreshing = false
        }


    }

    private fun getArticles()
    {
        viewModel.getArticlesByKeyword().observe(viewLifecycleOwner,  {
            lifecycleScope.launch {
                articlesListAdapter.submitData(it)
                articlesListAdapter.notifyDataSetChanged()

            }
        })
    }
    private fun getSavedArticles() {
            viewModel.searchSavedArticlesData().observe(viewLifecycleOwner, {

                lifecycleScope.launch {
                    it?.let {
                        articlesListAdapter.submitData(it)
                        articlesListAdapter.notifyDataSetChanged()

                    }
                }
            })
    }


}