package com.taehee.wordcard.presentation.ui.game

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.view.View
import androidx.core.content.ContextCompat
import com.taehee.presentation.R
import com.taehee.presentation.databinding.ViewholderGameBinding
import com.taehee.wordcard.domain.model.Game
import com.taehee.wordcard.presentation.ui.custom.CustomAdapter
import com.taehee.wordcard.presentation.ui.custom.DiffCallback

class GameRecyclerViewAdapter(
    onClick: (Game) -> Unit,
) : CustomAdapter<Game, ViewholderGameBinding>(
    R.layout.viewholder_game, DiffCallback.game(),
    bind = { item, binding ->

        binding.textView.apply {
            text = item.name
            visibility = if (item.state == Game.GameState.NONE) View.GONE else View.VISIBLE
        }

        binding.cardView.apply {
            setOnClickListener { onClick(item) }
            setCardBackgroundColor(
                ContextCompat.getColor(
                    binding.cardView.context,
                    if (item.state == Game.GameState.SUCCESS)
                        R.color.shrine_pink_100
                    else
                        R.color.shrine_pink_light
                )
            )
        }
        binding.cardView.cameraDistance =
            20000 * binding.root.context.resources.displayMetrics.density

        if (item.state == Game.GameState.FLIP) {
            val animatorSet = AnimatorInflater.loadAnimator(binding.root.context,
                R.animator.game_animation) as AnimatorSet
            val textAnimatorSet = AnimatorInflater.loadAnimator(binding.root.context,
                R.animator.game_text_animation) as AnimatorSet
            animatorSet.setTarget(binding.cardView)
            textAnimatorSet.setTarget(binding.textView)
            animatorSet.start()
            textAnimatorSet.start()
        }
    }
)