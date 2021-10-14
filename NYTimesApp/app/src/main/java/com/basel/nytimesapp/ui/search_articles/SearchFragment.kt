package com.basel.nytimesapp.ui.search_articles

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
import com.basel.nytimesapp.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.search_fragment.*

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    var keyword : String?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBtn.setOnClickListener {
            keyword=searchTxt.editableText.toString()
            if (keyword.isNullOrEmpty())
                searchTxt.error=resources.getString(R.string.mandatory_field)
            else
                navigateToArticlesFragment()
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }

    private fun navigateToArticlesFragment()
    {
        viewModel.getArticlesByKeyword(keyword)
        findNavController().navigate(SearchFragmentDirections.navigateSearchToArticles())
    }
}