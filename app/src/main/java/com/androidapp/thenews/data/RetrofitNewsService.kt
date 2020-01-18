package com.androidapp.thenews.data

import com.androidapp.thenews.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

/**
 * Created by S.Nur Uysal on 2019-11-03.
 */


private const val NEWS_API_BASE_URL = "https://newsapi.org/v2/"

class RetrofitNewsService : NewsService {

    private val api: NewsApi

    init {
        api = Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .client(providesOkHttpClient())
            .baseUrl(NEWS_API_BASE_URL)
            .build()
            .create(NewsApi::class.java)
    }

    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    override fun getArticles(category: String, searchQuery: String, page: Int) =
        api.getArticles("de", category, searchQuery, page, 10, BuildConfig.NEWS_API_KEY)


}