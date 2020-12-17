package com.example.core.http

interface EntityCallback<in T> {
    fun onSuccess(entity: T)
    fun onFailure(message: String?)
}