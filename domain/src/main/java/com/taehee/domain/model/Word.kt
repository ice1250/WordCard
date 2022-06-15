package com.taehee.domain.model

data class Word(
    val name: String,
    val time: Long
) {
    var color: String? = null
}