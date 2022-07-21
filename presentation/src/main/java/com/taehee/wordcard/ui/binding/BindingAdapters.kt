package com.taehee.wordcard.ui.binding

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taehee.domain.model.Card
import com.taehee.domain.model.Game
import com.taehee.domain.model.GithubRepo
import com.taehee.domain.model.Word
import com.taehee.wordcard.ui.base.UiState
import com.taehee.wordcard.ui.base.successOrNull
import com.taehee.wordcard.ui.edit.EditRecyclerViewAdapter
import com.taehee.wordcard.ui.game.GameRecyclerViewAdapter
import com.taehee.wordcard.ui.info.InfoRecyclerViewAdapter

@BindingAdapter("items")
fun RecyclerView.gameItems(items: List<Game>?) {
    (adapter as? GameRecyclerViewAdapter)?.submitList(items)
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

@BindingAdapter("cardShow")
fun CardView.bindCard(uiState: UiState<Card>) {
    isClickable = uiState !is UiState.Loading
    visibility = if (uiState is UiState.Error) View.GONE else View.VISIBLE
}

@BindingAdapter("card")
fun TextView.bindCard(uiState: UiState<Card>) {
    val card: Card? = uiState.successOrNull()
    if (card != null) {
        text = card.name
    }
}

@BindingAdapter("show")
fun TextView.bindShow(uiState: UiState<*>) {
    visibility = if (uiState is UiState.Error) View.VISIBLE else View.GONE
}

@BindingAdapter("show")
fun ProgressBar.bindShow(uiState: UiState<*>) {
    visibility = if (uiState is UiState.Loading) View.VISIBLE else View.GONE
}
