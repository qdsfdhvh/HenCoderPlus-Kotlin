package com.example.core.http

import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

object HttpClient : OkHttpClient() {

    @JvmStatic
    val INSTANCE get() = this

    private val gson = GsonBuilder().create()

    private fun <T> convert(json: String, type: Type): T {
        return gson.fromJson(json, type)
    }

    fun <T> get(path: String, type: Type, callback: EntityCallback<T>) {
        val request = Request.Builder()
            .url("https://api.hencoder.com/$path")
            .build()

        val call = INSTANCE.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure("网络异常")
            }

            override fun onResponse(call: Call, response: Response) {
                when(response.code()) {
                    in 200..299 -> {
                        val json = response.body()?.string().orEmpty()
                        callback.onSuccess(convert(json, type))
                    }
                    in 400..499 -> callback.onFailure("客户端错误")
                    in 500..599 -> callback.onFailure("服务器错误")
                    else -> callback.onFailure("位置错误")
                }
            }
        })
    }

}