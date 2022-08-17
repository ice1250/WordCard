package com.taehee.wordcard.app.di

import com.taehee.wordcard.domain.repository.GithubRepository
import com.taehee.wordcard.domain.repository.WordRepository
import com.taehee.wordcard.domain.usecase.github.GetGithubReposUseCase
import com.taehee.wordcard.domain.usecase.word.*
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
    fun provideGetGameUseCase(repository: WordRepository): GetGameUseCase {
        return GetGameUseCase(repository)
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
    fun provideGetGithubRepoUseCase(repository: GithubRepository): GetGithubReposUseCase {
        return GetGithubReposUseCase(repository)
    }
}