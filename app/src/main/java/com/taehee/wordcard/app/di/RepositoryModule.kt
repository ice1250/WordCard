package com.taehee.wordcard.app.di

import com.taehee.wordcard.data.repository.GithubRepositoryImpl
import com.taehee.wordcard.data.repository.WordRepositoryImpl
import com.taehee.wordcard.domain.repository.GithubRepository
import com.taehee.wordcard.domain.repository.WordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindWordRepository(repository: WordRepositoryImpl): WordRepository

    @Singleton
    @Binds
    abstract fun bindGithubRepository(repository: GithubRepositoryImpl): GithubRepository
}