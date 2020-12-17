package com.example.core

import android.app.Application
import android.content.Context

open class BaseApplication : Application() {

    companion object {
        lateinit var currentApplication: Context
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        currentApplication = this
    }
}