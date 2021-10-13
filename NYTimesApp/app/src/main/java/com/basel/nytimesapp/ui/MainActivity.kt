package com.basel.nytimesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.basel.nytimesapp.R
import com.basel.nytimesapp.adapters.SearchArticlesAdapter
import com.basel.nytimesapp.network.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
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

        lifecycleScope.launch {
            articlesListAdapter.loadStateFlow.collectLatest { loadStates ->

                if (loadStates.refresh is LoadState.Error)
                {
                    getSavedArticles()
                        //                    no_record_found.isVisible =true
                    (loadStates.refresh as LoadState.Error).error.message?.let {

//                        snackBar.showErrorMessage(it)
                    }

                }
                Toast.makeText(this@MainActivity,"not loading",Toast.LENGTH_LONG).show()

//                cl_progress_bar.isVisible=loadStates.refresh is LoadState.Loading
            }
        }


        viewModel.getArticlesData().observe(this, Observer {

            lifecycleScope.launch {
                Toast.makeText(this@MainActivity,"success",Toast.LENGTH_LONG).show()

                articlesListAdapter.submitData(it)
                articlesListAdapter.notifyDataSetChanged()

            }
        })


    }

    private fun getSavedArticles()
    {
        viewModel.getSavedArticlesData().observe(this, {

            lifecycleScope.launch {
                Toast.makeText(this@MainActivity,"success2",Toast.LENGTH_LONG).show()
                it?.let { articlesListAdapter.submitData(it)
                    articlesListAdapter.notifyDataSetChanged()
                }

            }
        })
    }
}