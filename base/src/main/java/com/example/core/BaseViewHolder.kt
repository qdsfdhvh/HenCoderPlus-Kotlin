package com.example.core

import android.util.SparseArray
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val viewSparseArray = SparseArray<View>()

    protected fun <T : View> getView(@IdRes id: Int): T {
        if (viewSparseArray.indexOfKey(id) >= 0) {
            @Suppress("UNCHECKED_CAST")
            return viewSparseArray[id] as T
        }
        val view = itemView.findViewById<T>(id)
        viewSparseArray.put(id, view)
        return view
    }

    protected fun setText(@IdRes id: Int, text: String?) {
        getView<TextView>(id).text = text
    }
}