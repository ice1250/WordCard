package com.taehee.data.source

import com.taehee.domain.model.Card

interface CardDataSource {

    fun getCard(name: String?): Card?
}