package com.github.joaotfernandes.newspagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.thenews.R
import com.androidapp.thenews.entity.Articles
import com.androidapp.thenews.rx.RxBus
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article.view.*

class ArticlesAdapter :
    PagedListAdapter<Articles, ArticlesAdapter.ArticlesViewHolder>(ArticleDiff) {

    class ArticlesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(articles: Articles) {
            view.titleTextView.text = articles.title
            view.descriptionTextView.text = articles.description
            view.sourceTextView.text = articles.source?.name
            view.publishedAtTextView.text = articles.publishedAt

            Glide.with(view.context)
                .load(articles.urlToImage)
                .placeholder(R.drawable.ic_picture)
                .into(view.ivArticle)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticlesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticlesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        var articles = getItem(position)

        articles
            ?.let { holder.bind(it) }
        holder.view.setOnClickListener {
            RxBus.publish(ArticleClickEvent(articles))
        }
    }

}

private object ArticleDiff : DiffUtil.ItemCallback<Articles>() {

    override fun areItemsTheSame(oldItem: Articles, newItem: Articles) = oldItem.title.equals(newItem.title)

    override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
        // In this case, if items are the same then content will always be the same
        return true
    }

}

data class ArticleClickEvent(val articles: Articles?)