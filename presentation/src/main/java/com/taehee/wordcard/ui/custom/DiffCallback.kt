package com.taehee.wordcard.ui.custom

import androidx.recyclerview.widget.DiffUtil
import com.taehee.domain.model.Game
import com.taehee.domain.model.Word

class DiffCallback {

    companion object {

        fun word(): DiffUtil.ItemCallback<Word> {
            return object : DiffUtil.ItemCallback<Word>() {
                override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
                    return oldItem.name == newItem.name && oldItem.time == newItem.time
                }

                override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
                    return oldItem == newItem
                }
            }
        }

        fun game(): DiffUtil.ItemCallback<Game> {
            return object : DiffUtil.ItemCallback<Game>() {
                override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                    return oldItem.name == newItem.name && oldItem.state == newItem.state && oldItem.num == newItem.num
                }

                override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                    return oldItem == newItem
                }
            }
        }
    }
}