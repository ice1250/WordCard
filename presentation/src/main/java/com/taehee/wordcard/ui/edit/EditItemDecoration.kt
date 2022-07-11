package com.taehee.wordcard.ui.edit

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EditItemDecoration(
    private val spacing: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildLayoutPosition(view)

        if (position > 0) {
            outRect.top = spacing
        }
        outRect.bottom = spacing

    }
}