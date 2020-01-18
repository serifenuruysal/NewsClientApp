package com.androidapp.thenews.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.androidapp.thenews.entity.Articles


/**
 * Created by S.Nur Uysal on 2019-11-03.
 */
class ArticlesDataSourceFactory(
    private val category: String
) : DataSource.Factory<Int, Articles>() {
    var textSearch: String = ""

    var postLiveData = MutableLiveData<ArticlesPagedDataSource>()

    override fun create(): DataSource<Int, Articles> {
        val dataSource = ArticlesPagedDataSource(category, textSearch)

        // Keep reference to the data source with a MutableLiveData reference
        postLiveData.postValue(dataSource)

        return dataSource
    }

    fun search(text: String) {
        this.textSearch = text
    }
}
