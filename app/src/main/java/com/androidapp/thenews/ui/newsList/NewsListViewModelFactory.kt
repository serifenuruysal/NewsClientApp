package com.androidapp.thenews.ui.categoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidapp.thenews.entity.Category

/**
 * Created by S.Nur Uysal on 2019-11-03.
 */
class NewsListViewModelFactory(val category: Category) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsListViewModel(category) as T
    }
}