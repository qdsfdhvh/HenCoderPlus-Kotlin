package com.example.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.app.R
import com.example.core.utils.Utils
import kotlin.random.Random

class CodeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    private val paint = Paint()

    init {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        gravity = Gravity.CENTER
        setBackgroundColor(getColor(R.color.colorPrimary))
        setTextColor(Color.WHITE)

        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.color = getColor(R.color.colorAccent)
        paint.strokeWidth = Utils.dp2px(6f)

        updateCode()
    }

    fun updateCode() {
        val random = Random.Default.nextInt(CODE_LIST.size)
        val code = CODE_LIST[random]
        text = code
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(0f, height.toFloat(), width.toFloat(), 0f, paint)
        super.onDraw(canvas)
    }

    @ColorInt
    private fun getColor(@ColorRes id: Int): Int {
        return ContextCompat.getColor(context, id)
    }

    companion object {
        private val CODE_LIST = arrayOf(
            "kotlin",
            "android",
            "java",
            "http",
            "https",
            "okhttp",
            "retrofit",
            "tcp/ip"
        )
    }
}