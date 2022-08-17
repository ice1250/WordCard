package com.taehee.wordcard.presentation.ui.info

import com.taehee.wordcard.domain.model.GithubRepo
import com.taehee.presentation.R
import com.taehee.presentation.databinding.ViewholderInfoBinding
import com.taehee.wordcard.presentation.ui.custom.CustomAdapter
import com.taehee.wordcard.presentation.ui.custom.DiffCallback

class InfoRecyclerViewAdapter(
    onClick: (GithubRepo) -> Unit,
) : CustomAdapter<GithubRepo, ViewholderInfoBinding>(
    R.layout.viewholder_info,
    DiffCallback.info(),
    bind = { item, binding ->
        binding.textView.text = item.name
        binding.cardView.setOnClickListener { onClick(item) }
    }
)