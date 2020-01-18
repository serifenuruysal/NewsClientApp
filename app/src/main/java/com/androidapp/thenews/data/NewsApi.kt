package com.androidapp.thenews.data

import com.androidapp.thenews.entity.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Created by S.Nur Uysal on 2019-11-03.
 */
interface NewsApi {

    @GET("top-headlines")
    fun getArticles(
        @Query("country") country: String?,
        @Query("category") sources: String?,
        @Query("q") searchQuery: String?,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Header("X-Api-Key") apiKey: String
    ): Call<NewsResponse>

}