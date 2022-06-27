package com.taehee.domain.model

import androidx.recyclerview.widget.DiffUtil

data class Word(
    val name: String,
    val time: Long,
) {
    var color: Int = 0

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem.name == newItem.name && oldItem.time == newItem.time
            }

            override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem == newItem
            }
        }
    }

}