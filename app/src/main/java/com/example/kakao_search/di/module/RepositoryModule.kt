package com.example.kakao_search.di.module

import com.example.kakao_search.data.dataSource.SearchDetailRepositoryImpl
import com.example.kakao_search.domain.repository.SearchRepository
import com.example.kakao_search.data.dataSource.SearchRepositoryImpl
import com.example.kakao_search.domain.repository.SearchDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Singleton
    @Binds
    abstract fun bindSearchDetailRepository(searchDetailRepositoryImpl: SearchDetailRepositoryImpl): SearchDetailRepository

}