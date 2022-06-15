package com.taehee.wordcard.di

import com.taehee.data.source.WordDataSource
import com.taehee.data.source.WordDataSourceImpl
import com.taehee.device.source.TtsSource
import com.taehee.device.source.TtsSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindWordDataSource(source: WordDataSourceImpl): WordDataSource

    @Singleton
    @Binds
    abstract fun bindTtsDataSource(source: TtsSourceImpl): TtsSource
}