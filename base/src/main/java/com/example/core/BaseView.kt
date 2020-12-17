package com.example.core

interface BaseView<out T> {
    val presenter: T
}