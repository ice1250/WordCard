package com.taehee.wordcard.app.di

import com.taehee.wordcard.data.source.GithubRemoteSource
import com.taehee.wordcard.data.source.GithubRemoteSourceImpl
import com.taehee.wordcard.data.source.WordDataSource
import com.taehee.wordcard.data.source.WordDataSourceImpl
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
    abstract fun bindGithubRemoteSource(source: GithubRemoteSourceImpl): GithubRemoteSource
}