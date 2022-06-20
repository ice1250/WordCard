package com.taehee.wordcard.ui.game

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.taehee.domain.model.Word
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.ViewholderGameBinding

class GameRecyclerViewAdapter() :
    RecyclerView.Adapter<GameRecyclerViewAdapter.GameRecyclerViewHolder>() {

    private var items: List<Word> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameRecyclerViewHolder {
        val binding: ViewholderGameBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.viewholder_game,
            parent,
            false
        )
        binding.root.minimumHeight = parent.measuredHeight / 4
        return GameRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameRecyclerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Word>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class GameRecyclerViewHolder(
        private val binding: ViewholderGameBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Word) {
            binding.textView.text = item.name
//            binding.textView.text = item.name
        }

    }
}