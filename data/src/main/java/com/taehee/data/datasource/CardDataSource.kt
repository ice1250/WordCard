package com.taehee.data.datasource

import com.taehee.domain.model.Card

interface CardDataSource {

    fun getCard(name: String?): Card?
}