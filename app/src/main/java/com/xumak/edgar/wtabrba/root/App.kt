package com.xumak.edgar.wtabrba.root

import android.app.Application

class App : Application() {
    lateinit var component : ApplicationComponet

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponet.builder()
            .build()

        component.inject(this)
    }
}