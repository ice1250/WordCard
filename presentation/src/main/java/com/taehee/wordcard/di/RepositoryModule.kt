package com.taehee.wordcard.di

import com.taehee.data.repository.WordRepositoryImpl
import com.taehee.device.repository.TtsRepositoryImpl
import com.taehee.domain.repository.TtsRepository
import com.taehee.domain.repository.WordRepository
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
    abstract fun bindTtsRepository(repository: TtsRepositoryImpl): TtsRepository
}