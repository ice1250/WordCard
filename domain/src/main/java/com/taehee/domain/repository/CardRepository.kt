package com.taehee.domain.repository

import com.taehee.domain.model.Card

interface CardRepository {

    suspend fun getCard(name: String?): Card?
}