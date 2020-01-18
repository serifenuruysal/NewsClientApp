package com.androidapp.thenews.ui.categoryList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by S.Nur Uysal on 2019-11-03.
 */
class CategoryViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}