package com.taehee.yuencard.ui.edit

import com.taehee.domain.model.Word

interface OnEditClickListener {

    fun onInsertClick(text: String)

    fun onDeleteClick(word: Word)

    fun onClick(word: Word)
}