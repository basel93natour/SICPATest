package com.basel.nytimesapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
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
class MainFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule= InstantTaskExecutorRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun clickSearch_navigateToSearchFragment()
    {
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<MainFragment>()
        {
            Navigation.setViewNavController(requireView(),navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.search_articles)).perform(ViewActions.click())
        Mockito.verify(navController).navigate(MainFragmentDirections.navigateMainToSearch())
    }

//    @Test
//    fun clickMostViewed_navigateToArticlesFragment()
//    {
//        val navController = Mockito.mock(NavController::class.java)
//        launchFragmentInHiltContainer<MainFragment>()
//        {
//            Navigation.setViewNavController(requireView(),navController)
//        }
//        Espresso.onView(ViewMatchers.withId(R.id.most_viewed)).perform(ViewActions.click())
//        Mockito.verify(navController).navigate(MainFragmentDirections.navigateMainToPopular())
//    }

    @Test
    fun clickBackBtnSearchFragment_NotEmptySearchNavigateToMainFragment()
    {
        val navController= Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<MainFragment>()
        {
            androidx.navigation.Navigation.setViewNavController(requireView(),navController)
        }
        Espresso.pressBack()

        Mockito.verify(navController).popBackStack()
    }


}