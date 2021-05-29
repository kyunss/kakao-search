package com.example.kakao_search.di.module

import android.content.Context
import com.example.kakao_search.BuildConfig
import com.example.kakao_search.data.network.KakaoApiService
import com.example.kakao_search.data.network.interceptor.TokenInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [NetworkModule.ProvideModule::class])
@InstallIn(SingletonComponent::class)
internal interface NetworkModule {

    @Module
    @InstallIn(SingletonComponent::class)
    class ProvideModule {
        @Singleton
        @Provides
        fun provideGson(): Gson {
            return GsonBuilder()
                .create()
        }

        @Singleton
        @Provides
        fun provideGsonConverterFactory(gson: Gson): Converter.Factory {
            return GsonConverterFactory.create(gson)
        }

        @Singleton
        @Provides
        fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor())
                .build()
        }

        @Singleton
        @Provides
        fun provideRetrofitApi(
            gsonConverterFactory: Converter.Factory,
            okHttpClient: OkHttpClient
        ): KakaoApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.KAKAO_BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build()

            return retrofit.create(KakaoApiService::class.java)
        }
    }

}