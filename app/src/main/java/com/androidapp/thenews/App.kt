package com.androidapp.thenews

import android.app.Application
import com.androidapp.thenews.di.module
import org.koin.android.ext.android.startKoin

/**
 * Created by S.Nur Uysal on 2019-11-03.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(module))
    }
}