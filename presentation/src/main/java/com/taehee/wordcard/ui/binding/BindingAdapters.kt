package com.taehee.wordcard.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taehee.domain.model.Game
import com.taehee.domain.model.GithubRepo
import com.taehee.domain.model.Word
import com.taehee.wordcard.ui.base.UiState
import com.taehee.wordcard.ui.base.successOrNull
import com.taehee.wordcard.ui.edit.EditRecyclerViewAdapter
import com.taehee.wordcard.ui.game.GameRecyclerViewAdapter
import com.taehee.wordcard.ui.info.InfoRecyclerViewAdapter

@BindingAdapter("items")
fun RecyclerView.items(items: List<Word>?) {
    (adapter as? EditRecyclerViewAdapter)?.submitList(items)
}

@BindingAdapter("items")
fun RecyclerView.gameItems(items: List<Game>?) {
    (adapter as? GameRecyclerViewAdapter)?.submitList(items)
}

@BindingAdapter("items")
fun RecyclerView.infoItems(items: List<GithubRepo>?) {
    (adapter as? InfoRecyclerViewAdapter)?.submitList(items)
}

@BindingAdapter("wordsItems")
fun RecyclerView.bindWordsItems(uiState: UiState<List<Word>>) {
    val editAdapter = this.adapter
    if (editAdapter is EditRecyclerViewAdapter) {
        editAdapter.submitList(uiState.successOrNull())
    }
}

@BindingAdapter("infoItems")
fun RecyclerView.bindInfoItems(uiState: UiState<List<GithubRepo>>) {
    val editAdapter = this.adapter
    if (editAdapter is InfoRecyclerViewAdapter) {
        editAdapter.submitList(uiState.successOrNull())
    }
}
