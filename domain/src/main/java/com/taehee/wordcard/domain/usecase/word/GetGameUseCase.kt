package com.taehee.wordcard.domain.usecase.word

import com.taehee.wordcard.domain.model.Game
import com.taehee.wordcard.domain.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetGameUseCase(private val repository: WordRepository) {

    operator fun invoke(
        scope: CoroutineScope,
        onResult: (List<Game>?) -> Unit = {},
    ) {
        scope.launch(Dispatchers.Main) {
            onResult(withContext(Dispatchers.IO) {
                repository.getGame()
            })
        }
    }

    suspend operator fun invoke(
    ): List<Game> {
        return repository.getGame()
    }
}