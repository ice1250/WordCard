package com.taehee.wordcard.ui.edit

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.taehee.domain.model.Word
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.ViewholderEditBinding

class EditRecyclerViewAdapter(private val listener: OnEditClickListener) :
    RecyclerView.Adapter<EditRecyclerViewAdapter.EditRecyclerViewHolder>() {

    private var items: List<Word> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditRecyclerViewHolder {
        return EditRecyclerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.viewholder_edit,
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: EditRecyclerViewHolder, position: Int) {
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

    inner class EditRecyclerViewHolder(
        private val binding: ViewholderEditBinding,
        private val listener: OnEditClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Word) {
            binding.textView.text = item.name
            binding.delete.setOnClickListener { listener.onDeleteClick(item) }
            binding.root.setOnClickListener { listener.onClick(item) }
        }

    }
}