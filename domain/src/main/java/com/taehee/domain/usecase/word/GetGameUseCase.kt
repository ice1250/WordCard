package com.taehee.domain.usecase.word

import com.taehee.domain.model.Game
import com.taehee.domain.repository.WordRepository
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
}