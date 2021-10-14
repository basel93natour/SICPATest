package com.basel.nytimesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.basel.nytimesapp.R
import com.basel.nytimesapp.data.Constants
import com.basel.nytimesapp.ui.search_articles.SearchFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        search_articles.setOnClickListener {
            navigateToSearchFragment()
        }

        most_viewed.setOnClickListener {
            navigateToArticlesFragment(Constants.ArticlesTypes.MOST_VIEWED)
        }

        most_emailed.setOnClickListener {
            navigateToArticlesFragment(Constants.ArticlesTypes.MOST_EMAILED)
        }

        most_shared.setOnClickListener {
            navigateToArticlesFragment(Constants.ArticlesTypes.MOST_SHARED)
        }
    }

    private fun navigateToSearchFragment()
    {
        findNavController().navigate(MainFragmentDirections.navigateMainToSearch())
    }

    private fun navigateToArticlesFragment(articleType: Constants.ArticlesTypes)
    {
        viewModel.getPopularArticles(articleType)
        findNavController().navigate(MainFragmentDirections.navigateMainToPopular())
    }



}