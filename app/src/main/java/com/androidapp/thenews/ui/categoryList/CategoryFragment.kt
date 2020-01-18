package com.androidapp.thenews.ui.categoryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.androidapp.thenews.R
import com.androidapp.thenews.entity.Category
import kotlinx.android.synthetic.main.category_fragment.*

/**
 * Created by S.Nur Uysal on 2019-11-03.
 */
class CategoryFragment : Fragment() {

    val categoryViewModel: CategoryViewModel by lazy {
        ViewModelProviders.of(this, context?.let { CategoryViewModelFactory(it) }).get(
            CategoryViewModel::class.java
        )
    }

    private val stateObserver = Observer<MutableList<Category>> { state ->
        state?.let {
            initCategoryList(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.category_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSubscriptions()
    }

    fun initSubscriptions() {
        categoryViewModel.categoryList.observe(this, stateObserver)

    }

    fun initCategoryList(categoryList: MutableList<Category>) {
        rv_category.adapter = CategoryListAdapter(categoryList)
        rv_category.layoutManager = GridLayoutManager(context, 2)
    }


}