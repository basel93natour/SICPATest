package com.basel.nytimesapp.ui.search_articles

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.basel.nytimesapp.R
import com.basel.nytimesapp.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@MediumTest //this is an integration test
@HiltAndroidTest
@ExperimentalCoroutinesApi
class SearchArticlesFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule= InstantTaskExecutorRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun clickSearchBtn_navigateToArticlesFragment()
    {
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<SearchFragment>()
        {
            Navigation.setViewNavController(requireView(),navController)
        }
        onView(withId(R.id.searchTxt)).perform(ViewActions.replaceText("usa"))
        onView(withId(R.id.searchBtn)).perform(ViewActions.click())
        Mockito.verify(navController).navigate(SearchFragmentDirections.navigateSearchToArticles())
    }

    @Test
    fun clickBackBtnSearchFragment_NotEmptySearchNavigateToMainFragment()
    {
        val navController= Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<SearchArticlesFragment>()
        {
            androidx.navigation.Navigation.setViewNavController(requireView(),navController)
        }
        Espresso.pressBack()

        Mockito.verify(navController).popBackStack()
    }

    @Test
    fun clickBackBtnSearchFragment_EmptySearchThrowError()
    {
        launchFragmentInHiltContainer<SearchFragment>() {}
        onView(withId(R.id.searchBtn)).perform(ViewActions.click())
        onView(withId(R.id.searchTxt)).check(matches(hasErrorText("Mandatory Field")));

    }

}