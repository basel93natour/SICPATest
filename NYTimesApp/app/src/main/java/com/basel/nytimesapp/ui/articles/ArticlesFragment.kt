package com.basel.nytimesapp.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.basel.nytimesapp.R
import com.basel.nytimesapp.adapters.PopularArticlesAdapter
import com.basel.nytimesapp.data.Constants
import com.basel.nytimesapp.data.models.popular_articles.PopularArticlesModel
import com.basel.nytimesapp.network.Resource
import com.basel.nytimesapp.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.articles_fragment.*
import kotlinx.android.synthetic.main.page_indicators.*

@AndroidEntryPoint
class ArticlesFragment() : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private val articlesList = ArrayList<PopularArticlesModel>()

    private val popularArticlesListAdapter by lazy {
        PopularArticlesAdapter(articlesList)
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

        swiperefresh.isEnabled=false

        var layoutManager = LinearLayoutManager(requireContext())
        rv_articles.layoutManager=layoutManager
        rv_articles.adapter= popularArticlesListAdapter

        viewModel.mostViewedArticles.observe(viewLifecycleOwner, Observer { response->
            when(response)
            {
                is Resource.Loading->
                {
                    cl_progress_bar.isVisible=true
                }
                is Resource.Success ->
                {
                    cl_progress_bar.isVisible=false
                    response.data?.let {
                        articlesList.addAll(it)
                        popularArticlesListAdapter.notifyDataSetChanged()
                    }
                    no_record_found.isVisible=articlesList.size==0

                }
                is Resource.Error ->
                {
                    cl_progress_bar.isVisible=false
                    no_record_found.isVisible=true
                }
            }

        })

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }
}