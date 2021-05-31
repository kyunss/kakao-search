package com.example.kakao_search.di.module

import com.example.kakao_search.data.dataSource.*
import com.example.kakao_search.data.dataSource.LocalSource
import com.example.kakao_search.data.dataSource.LocalSourceImpl
import com.example.kakao_search.data.dataSource.RemoteSource
import com.example.kakao_search.data.dataSource.RemoteSourceImpl
import com.example.kakao_search.data.dataSource.SearchResultCache
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindsRemoteDataSource(remoteSourceImpl: RemoteSourceImpl): RemoteSource

    @Singleton
    @Binds
    abstract fun bindsLocalDataSource(localSourceImpl: LocalSourceImpl): LocalSource

    @Module
    @InstallIn(SingletonComponent::class)
    class ProvideModule {
        @Singleton
        @Provides
        fun provideSearchResultCache(): SearchResultCache {
            return SearchResultCache()
        }
    }

}