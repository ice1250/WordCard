package com.taehee.wordcard.ui.edit

import com.taehee.domain.model.Word

interface OnEditClickListener {

    fun onInsertClick(text: String)

    fun onDeleteClick(word: Word)

    fun onClick(word: Word)
}