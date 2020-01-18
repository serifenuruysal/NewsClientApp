package com.androidapp.thenews.ui.categoryList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.androidapp.thenews.domain.ArticlesDataSourceFactory
import com.androidapp.thenews.domain.ArticlesPagedDataSource
import com.androidapp.thenews.entity.Articles
import com.androidapp.thenews.entity.Category


/**
 * Created by S.Nur Uysal on 2019-11-03.
 */
class NewsListViewModel(val category: Category) : ViewModel() {
    var articles: LiveData<PagedList<Articles>>
    var liveDataSource: MutableLiveData<ArticlesPagedDataSource>? = null
    var searchText: String = ""
    val itemDataSourceFactory: ArticlesDataSourceFactory

    init {

        itemDataSourceFactory = ArticlesDataSourceFactory(category.categoryName)

        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.postLiveData

        //Getting PagedList config
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10).build()

        //Building the paged list
        articles = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)
            .build()
    }

    fun search(searchText: String) {
        this.searchText = searchText
        itemDataSourceFactory.search(searchText)
        articles.value?.dataSource?.invalidate()

    }


}