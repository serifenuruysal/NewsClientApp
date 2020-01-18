package com.androidapp.thenews.di

import androidx.paging.DataSource
import com.androidapp.thenews.data.NewsService
import com.androidapp.thenews.data.RetrofitNewsService
import com.androidapp.thenews.domain.ArticlesDataSourceFactory
import com.androidapp.thenews.entity.Articles
import org.koin.dsl.module.module

/**
 * Created by S.Nur Uysal on 2019-11-03.
 */

val module = module {
    single { RetrofitNewsService() as NewsService }

    single {
        ArticlesDataSourceFactory(get())
                as DataSource.Factory<Int, Articles>
    }

}