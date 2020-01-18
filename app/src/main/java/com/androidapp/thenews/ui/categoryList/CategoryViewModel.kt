package com.androidapp.thenews.ui.categoryList

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidapp.thenews.R
import com.androidapp.thenews.entity.Category

/**
 * Created by S.Nur Uysal on 2019-11-03.
 */
class CategoryViewModel(val context: Context) : ViewModel() {
    var categoryList: MutableLiveData<MutableList<Category>> = MutableLiveData()

    init {
        val categoryMutList: MutableList<Category> = mutableListOf()
        categoryMutList.add(Category(R.drawable.entertainment, getString(R.string.entertainment)))
        categoryMutList.add(Category(R.drawable.business, getString(R.string.business)))
        categoryMutList.add(Category(R.drawable.news, getString(R.string.general)))
        categoryMutList.add(Category(R.drawable.science, getString(R.string.science)))
        categoryMutList.add(Category(R.drawable.sports, getString(R.string.sports)))
        categoryMutList.add(Category(R.drawable.technology, getString(R.string.technology)))
        categoryMutList.add(Category(R.drawable.health, getString(R.string.health)))
        categoryList.value = categoryMutList

    }

    fun getString(id: Int): String {
        return context.getString(id)
    }

}