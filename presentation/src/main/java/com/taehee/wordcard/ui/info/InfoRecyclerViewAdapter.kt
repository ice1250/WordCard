package com.taehee.wordcard.ui.info

import com.taehee.domain.model.GithubRepo
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.ViewholderInfoBinding
import com.taehee.wordcard.ui.custom.CustomAdapter
import com.taehee.wordcard.ui.custom.DiffCallback

class InfoRecyclerViewAdapter : CustomAdapter<GithubRepo, ViewholderInfoBinding>(
    R.layout.viewholder_info,
    DiffCallback.info(),
    bind = { item, binding ->
        binding.textView.text = item.url
    }
)