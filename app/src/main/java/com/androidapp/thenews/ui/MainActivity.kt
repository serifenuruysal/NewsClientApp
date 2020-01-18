package com.androidapp.thenews.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.thenews.R
import com.androidapp.thenews.entity.Category
import com.androidapp.thenews.rx.RxBus
import com.androidapp.thenews.ui.categoryList.CategoryClickEvent
import com.androidapp.thenews.ui.categoryList.CategoryFragment
import com.androidapp.thenews.ui.newsList.NewsListFragment
import com.androidapp.thenews.ui.util.addFragment
import com.androidapp.thenews.ui.util.replaceFragment
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var subscribeCategorySelectedEvent: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        addFragment(CategoryFragment(), R.id.frame_main_content, true)
        subscribeObservable()
    }

    fun subscribeObservable() {

        subscribeCategorySelectedEvent =
            RxBus.listen(CategoryClickEvent::class.java).subscribe {
                it
                val category: Category = it.category
                replaceFragment(NewsListFragment.newInstance(category), R.id.frame_main_content, true)

            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    fun unSubscribeObservable() {
        if (!subscribeCategorySelectedEvent.isDisposed) {
            subscribeCategorySelectedEvent.dispose()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unSubscribeObservable()
    }
}
