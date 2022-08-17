package com.taehee.wordcard.presentation.ui.custom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class CustomAdapter<T : Any, B : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
    itemCallback: DiffUtil.ItemCallback<T>,
    inline val bind: (item: T, binding: B) -> Unit,
) : ListAdapter<T, CustomAdapter.CustomViewHolder<B>>(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder<B> =
        CustomViewHolder(layoutResId, parent)

    override fun onBindViewHolder(holder: CustomViewHolder<B>, position: Int) =
        bind(getItem(position), holder.binding)

    class CustomViewHolder<B : ViewDataBinding>(
        @LayoutRes layoutResId: Int,
        parent: ViewGroup,
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(layoutResId, parent, false),
    ) {
        val binding: B = DataBindingUtil.bind(itemView)!!
    }
}