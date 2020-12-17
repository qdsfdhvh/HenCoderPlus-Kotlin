package com.example.core.utils

import android.content.Context
import com.example.core.BaseApplication
import com.example.core.R

object CacheUtils {

    private val preferences by lazy(LazyThreadSafetyMode.NONE) {
        val context = BaseApplication.currentApplication()
        context.getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }

    @JvmStatic
    fun save(key: String, value: String?) {
        preferences.edit()
            .putString(key, value)
            .apply()
    }

    @JvmStatic
    fun get(key: String): String? {
        return preferences.getString(key, null)
    }

}