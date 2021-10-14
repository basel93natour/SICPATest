package com.basel.nytimesapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.map
import com.basel.MainCoroutineRule
import com.basel.collectData
import com.basel.getOrAwaitValue
import com.basel.nytimesapp.data.Constants
import com.basel.nytimesapp.repo.FakeDataRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {
    private lateinit var viewmodel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule= MainCoroutineRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setup() {
        viewmodel= MainViewModel(FakeDataRepository())
    }


}