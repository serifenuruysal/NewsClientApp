package com.androidapp.thenews.ui.newsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.thenews.R
import com.androidapp.thenews.entity.Category
import com.androidapp.thenews.rx.RxBus
import com.androidapp.thenews.ui.categoryList.NewsListViewModel
import com.androidapp.thenews.ui.categoryList.NewsListViewModelFactory
import com.androidapp.thenews.ui.util.afterTextChanged
import com.androidapp.thenews.ui.web.WebFragmentDialog
import com.github.joaotfernandes.newspagination.ArticleClickEvent
import com.github.joaotfernandes.newspagination.ArticlesAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.category_fragment.rv_category
import kotlinx.android.synthetic.main.news_list_fragment.*


/**
 * Created by S.Nur Uysal on 2019-11-03.
 */

private const val CATEGORY = "CATEGORY"

class NewsListFragment : Fragment() {

    lateinit var adapter: ArticlesAdapter


    lateinit var subscribeArticleClickEvent: Disposable

    lateinit var newsListViewModel: NewsListViewModel

    companion object {
        fun newInstance(selectedCategory: Category): NewsListFragment {
            val fragment = NewsListFragment()
            val arguments = Bundle()
            arguments.putParcelable(CATEGORY, selectedCategory)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            var category = arguments?.getParcelable<Category>(CATEGORY)!!
            newsListViewModel = ViewModelProviders.of(this, NewsListViewModelFactory(category)).get(
                NewsListViewModel::class.java
            )
        }
        initSearchView()
        initNewsList()
        initSubscriptions()

    }

    fun initSearchView() {
        et_searc_view.afterTextChanged { newsListViewModel.search(it) }
    }

    fun initSubscriptions() {
        newsListViewModel.articles.observe(this, Observer {

            adapter.submitList(it)
        })

        subscribeArticleClickEvent =
            RxBus.listen(ArticleClickEvent::class.java).subscribe {
                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                val prev = activity?.supportFragmentManager?.findFragmentByTag("dialog")
                if (prev != null) {
                    fragmentTransaction.remove(prev)
                }
                fragmentTransaction.addToBackStack(null)
                WebFragmentDialog.newInstance(it.articles?.url).show(fragmentTransaction, "dialog")

            }
    }

    fun initNewsList() {

        adapter = ArticlesAdapter()
        rv_category.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_category.adapter = adapter
    }


    override fun onDestroy() {
        super.onDestroy()
        if (!subscribeArticleClickEvent.isDisposed) {
            subscribeArticleClickEvent.dispose()
        }
    }
}