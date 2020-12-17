package com.example.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.core.BaseViewHolder
import com.example.lesson.entity.Lesson

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    private var list: List<Lesson> = emptyList()

    fun updateAndNotify(list: List<Lesson>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    class LessonViewHolder(itemView: View) : BaseViewHolder(itemView) {
        fun onBind(lesson: Lesson) {
            setText(R.id.tv_date, lesson.date ?: "日期待定")
            setText(R.id.tv_content, lesson.content)

            val colorRes = when (lesson.state) {
                Lesson.State.PLAYBACK -> R.color.playback
                Lesson.State.LIVE -> R.color.live
                Lesson.State.WAIT -> R.color.wait
                else -> R.color.playback
            }
            val backgroundColor = getColor(colorRes)
            getView<View>(R.id.tv_state).setBackgroundColor(backgroundColor)
        }

        @ColorInt
        private fun getColor(@ColorRes id: Int): Int {
            return ContextCompat.getColor(itemView.context, id)
        }

        companion object {
            fun create(parent: ViewGroup): LessonViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_lesson, parent, false)
                return LessonViewHolder(itemView)
            }
        }
    }

}