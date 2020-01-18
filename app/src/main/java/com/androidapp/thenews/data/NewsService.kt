package com.androidapp.thenews.data

import com.androidapp.thenews.entity.NewsResponse
import retrofit2.Call


/**
 * Created by S.Nur Uysal on 2019-11-03.
 */

private const val DEFAULT_HEADLINES_PAGE = 1
private const val DEFAULT_HEADLINES_PER_PAGE = 10

interface NewsService {

    /**
     * Provides articles that have a match the given [keywords].
     *
     * Articles are sorted by the earliest date published first.
     *
     * @param country
     * @param category
     * @param page should be greater or equal than 1 and allows to page through the results if the total results found is greater than
     * [pageSize]. By default the first page will be returned.
     * @param pageSize the number of results to return. 10 is the default value, 100 is the maximum
     */
    fun getArticles(
        category: String,
        searchQuery: String,
        page: Int = DEFAULT_HEADLINES_PAGE
    ): Call<NewsResponse>
}