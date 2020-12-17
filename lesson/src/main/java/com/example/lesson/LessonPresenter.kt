package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.Utils
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import java.lang.ref.WeakReference

class LessonPresenter(activity: LessonActivity) {

    private val weak = WeakReference<LessonActivity>(activity)

    private val lessons = ArrayList<Lesson>()

    private val type = object : TypeToken<List<Lesson>>() {}.type

    fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onSuccess(entity: List<Lesson>) {
                weak.get()?.let { activity ->
                    activity.runOnUiThread {
                        activity.showResult(lessons)
                    }
                }
            }

            override fun onFailure(message: String) {
                weak.get()?.runOnUiThread {
                    Utils.toast(message)
                }
            }
        })
    }

    fun showPlayback() {
        weak.get()?.showResult(lessons.filter {
            it.state == Lesson.State.PLAYBACK
        })
    }

    companion object {
        private const val LESSON_PATH = "lessons"
    }
}