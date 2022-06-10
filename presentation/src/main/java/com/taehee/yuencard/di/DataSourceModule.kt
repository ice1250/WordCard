package com.taehee.yuencard.di

import com.taehee.data.datasource.*
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
    abstract fun bindCardDataSource(source: CardDataSourceImpl): CardDataSource

    @Singleton
    @Binds
    abstract fun bindWordDataSource(source: WordDataSourceImpl): WordDataSource

    @Singleton
    @Binds
    abstract fun bindTtsDataSource(source: TtsDataSourceImpl): TtsDataSource
}