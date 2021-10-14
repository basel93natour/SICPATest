package com.basel.nytimesapp.di

import android.content.Context
import androidx.room.Room
import com.basel.nytimesapp.data.local.AppDatabase
import com.basel.nytimesapp.data.local.PopularArticlesDao
import com.basel.nytimesapp.data.local.SearchArticlesDao
import com.basel.nytimesapp.network.NYTimesApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.nytimes.com/svc/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor()
            .apply { this.level= HttpLoggingInterceptor.Level.BODY })
            .build())
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideRemoteDataService ( retrofit: Retrofit) : NYTimesApiService=retrofit.create(NYTimesApiService::class.java)


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "RssReader"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providePopularArticlesDao(appDatabase: AppDatabase): PopularArticlesDao {
        return appDatabase.popularArticlesDao()
    }


    @Provides
    fun provideSearchArticlesDao(appDatabase: AppDatabase): SearchArticlesDao {
        return appDatabase.searchArticlesDao()
    }

}