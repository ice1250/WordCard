package com.taehee.data.repository

import com.taehee.data.datasource.CardDataSource
import com.taehee.domain.model.Card
import com.taehee.domain.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val dataSource: CardDataSource,
) : CardRepository {

    override suspend fun getCard(name: String?): Card? {
        return dataSource.getCard(name)
    }
}