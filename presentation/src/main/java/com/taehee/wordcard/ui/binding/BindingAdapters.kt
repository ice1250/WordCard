package com.taehee.wordcard.ui.binding

import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taehee.domain.model.Card
import com.taehee.domain.model.Game
import com.taehee.domain.model.GithubRepo
import com.taehee.domain.model.Word
import com.taehee.wordcard.ui.base.UiState
import com.taehee.wordcard.ui.base.successOrNull
import com.taehee.wordcard.ui.custom.parade
import com.taehee.wordcard.ui.edit.EditItemDecoration
import com.taehee.wordcard.ui.edit.EditRecyclerViewAdapter
import com.taehee.wordcard.ui.edit.EditViewModel
import com.taehee.wordcard.ui.game.GameItemDecoration
import com.taehee.wordcard.ui.game.GameRecyclerViewAdapter
import com.taehee.wordcard.ui.game.GameUiState
import com.taehee.wordcard.ui.game.GameViewModel
import com.taehee.wordcard.ui.info.InfoRecyclerViewAdapter
import com.taehee.wordcard.ui.main.MainViewModel
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Size
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit

// CardFragment

@BindingAdapter("bind")
fun CardView.bind(
    uiState: UiState<Card>,
) {
    isClickable = uiState !is UiState.Loading
    isVisible = uiState !is UiState.Error
}

@BindingAdapter("bind")
fun KonfettiView.bind(motionEvent: MotionEvent?) {
    motionEvent?.apply {
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            start(Party(
                angle = 10,
                size = listOf(Size.LARGE),
                position = Position.Absolute(rawX, rawY),
                emitter = Emitter(100, TimeUnit.MILLISECONDS).perSecond(50)
            ))
        }
    }
}

@BindingAdapter("bind")
fun TextView.bind(uiState: UiState<Card>) {
    uiState.successOrNull()?.apply { text = name }
}

@BindingAdapter("gameViewModel", "items")
fun RecyclerView.bind(viewModel: GameViewModel, items: List<Game>?) {
    if (adapter == null) {
        setHasFixedSize(true)
        adapter = GameRecyclerViewAdapter { viewModel.select(it) }
        addItemDecoration(GameItemDecoration(4, 10))
    }
    (adapter as? GameRecyclerViewAdapter)?.submitList(items)
}

@BindingAdapter("bind")
fun KonfettiView.bind(uiState: GameUiState) {
    if (uiState.isGameWin) {
        start(parade())
    }
}

@BindingAdapter("bind")
fun Button.bind(uiState: GameUiState) {
    isVisible = uiState.isGameWin
}

@BindingAdapter("editViewModel", "sharedViewModel", "items")
fun RecyclerView.bind(
    editViewModel: EditViewModel,
    sharedViewModel: MainViewModel,
    uiState: UiState<List<Word>>,
) {
    if (adapter == null) {
        setHasFixedSize(true)
        adapter = EditRecyclerViewAdapter {
            editViewModel.deleteWord(it)
            sharedViewModel.wordChanged()
        }
        addItemDecoration(EditItemDecoration(4))
    }
    (adapter as? EditRecyclerViewAdapter)?.submitList(uiState.successOrNull())

}

@BindingAdapter("editText", "editViewModel", "sharedViewModel", "recyclerView")
fun Button.bind(
    editText: EditText,
    editViewModel: EditViewModel,
    sharedViewModel: MainViewModel,
    recyclerView: RecyclerView,
) {
    setOnClickListener {
        editText.text.toString().isNotEmpty().apply {
            editViewModel.addWord(editText.text.toString())
            editText.text.clear()
            sharedViewModel.wordChanged()

            recyclerView.adapter?.itemCount.also { count ->
                if (count != null && count > 0) {
                    postDelayed({
                        recyclerView.smoothScrollToPosition(count)
                    }, 100)
                }
            }
        }
    }
}

@BindingAdapter("infoItems")
fun RecyclerView.bindInfoItems(uiState: UiState<List<GithubRepo>>) {
    val editAdapter = this.adapter
    if (editAdapter is InfoRecyclerViewAdapter) {
        editAdapter.submitList(uiState.successOrNull())
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