package com.example.kakao_search.di.module

import com.example.kakao_search.data.dataSource.RemoteSource
import com.example.kakao_search.data.dataSource.RemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindsRemoteDataSource(remoteSourceImpl: RemoteSourceImpl): RemoteSource

}