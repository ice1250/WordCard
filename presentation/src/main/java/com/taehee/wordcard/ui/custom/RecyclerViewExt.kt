package com.taehee.wordcard.ui.custom

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.isScrollable() = canScrollVertically(1) || canScrollVertically(-1)

fun RecyclerView.setStackFromEnd(stackFromEnd: Boolean) {
    (layoutManager as? LinearLayoutManager)?.stackFromEnd = stackFromEnd
}