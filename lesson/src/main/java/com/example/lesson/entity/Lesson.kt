package com.example.lesson.entity

data class Lesson(
    var date: String? = null,
    var content: String = "",
    var state: State? = null
) {
    enum class State {
        PLAYBACK {
            override fun stateName(): String = "有回放"
        },
        LIVE {
            override fun stateName(): String = "正在直播"
        },
        WAIT {
            override fun stateName(): String = "等待中"
        };

        abstract fun stateName(): String
    }
}