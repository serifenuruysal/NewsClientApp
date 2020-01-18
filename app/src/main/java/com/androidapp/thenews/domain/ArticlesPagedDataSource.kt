package com.androidapp.thenews.domain

import androidx.paging.PageKeyedDataSource
import com.androidapp.thenews.data.RetrofitNewsService
import com.androidapp.thenews.entity.Articles
import com.androidapp.thenews.entity.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by S.Nur Uysal on 2019-11-03.
 */


class ArticlesPagedDataSource(
    private val category: String,
    private val textSearch: String
) : PageKeyedDataSource<Int, Articles>() {

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {
        val service = RetrofitNewsService()
        val call = service.getArticles(category, textSearch, params.key)
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.articles
                    val key = if (params.key > 1) params.key - 1 else 0
                    responseItems.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
            }
        })
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Articles>) {
        val service = RetrofitNewsService()
        val call = service.getArticles(category, textSearch, FIRST_PAGE)
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.articles
                    responseItems.let {
                        callback.onResult(responseItems, null, FIRST_PAGE + 1)
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {
        val service = RetrofitNewsService()
        val call = service.getArticles(category, textSearch, params.key)
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.articles
                    val key = params.key + 1
                    responseItems.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
            }
        })
    }

    companion object {
        const val PAGE_SIZE = 10
        const val FIRST_PAGE = 0

    }
}