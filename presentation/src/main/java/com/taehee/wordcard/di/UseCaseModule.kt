package com.taehee.wordcard.di

import com.taehee.domain.repository.TtsRepository
import com.taehee.domain.repository.WordRepository
import com.taehee.domain.usecase.word.GetCardUseCase
import com.taehee.domain.usecase.tts.SpeakTtsUseCase
import com.taehee.domain.usecase.tts.StopTtsUseCase
import com.taehee.domain.usecase.word.AddWordUseCase
import com.taehee.domain.usecase.word.GetWordsUseCase
import com.taehee.domain.usecase.word.RemoveWordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetWordUseCase(repository: WordRepository): GetWordsUseCase {
        return GetWordsUseCase(repository)
    }

    @Provides
    fun provideGetCardUseCase(repository: WordRepository): GetCardUseCase {
        return GetCardUseCase(repository)
    }

    @Provides
    fun provideAddWordUseCase(repository: WordRepository): AddWordUseCase {
        return AddWordUseCase(repository)
    }

    @Provides
    fun provideRemoveWordUseCase(repository: WordRepository): RemoveWordUseCase {
        return RemoveWordUseCase(repository)
    }

    @Provides
    fun provideSpeakTtsUseCase(repository: TtsRepository): SpeakTtsUseCase {
        return SpeakTtsUseCase(repository)
    }

    @Provides
    fun provideStopTtsUseCase(repository: TtsRepository): StopTtsUseCase {
        return StopTtsUseCase(repository)
    }
}