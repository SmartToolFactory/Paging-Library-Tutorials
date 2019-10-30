package com.smarttoolfactory.tutorial3_1paging_rest_and_room

import android.content.Context
import com.smarttoolfactory.tutorial3_1paging_rest_and_room.api.GithubService
import com.smarttoolfactory.tutorial3_1paging_rest_and_room.data.LocalCache
import com.smarttoolfactory.tutorial3_1paging_rest_and_room.data.RepoDb
import com.smarttoolfactory.tutorial3_1paging_rest_and_room.data.Repository
import com.smarttoolfactory.tutorial3_1paging_rest_and_room.viewmodel.ViewModelFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

object Injection {

    @JvmStatic
    fun provideViewModelFactory(context: Context): ViewModelFactory {

        val repository =
            Repository(GithubService.create(), provideLocalCache(context.applicationContext))
        return ViewModelFactory(repository)
    }


    private fun provideLocalCache(context: Context): LocalCache {

        val repoDao = RepoDb.getInstance(context).reposDao()

        return LocalCache(repoDao, Executors.newFixedThreadPool(3))
    }
}