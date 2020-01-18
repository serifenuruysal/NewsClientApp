package com.androidapp.thenews.ui.categoryList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.thenews.R
import com.androidapp.thenews.entity.Category
import com.androidapp.thenews.rx.RxBus
import kotlinx.android.synthetic.main.item_category.view.*

/**
 * Created by S.Nur Uysal on 2019-10-25.
 */

class CategoryListAdapter(
    private val myDataset: List<Category>
) :
    RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        var category = myDataset.get(position)
        holder.view.tv_category_title.text = category.categoryName
        holder.view.iv_category_image.setImageResource(category.imageResource)

        holder.view.setOnClickListener {
            RxBus.publish(CategoryClickEvent(category))
        }
    }

    override fun getItemCount() = myDataset.size
}

data class CategoryClickEvent(val category: Category)