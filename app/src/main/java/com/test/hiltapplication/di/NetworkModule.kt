package com.test.hiltapplication.di

import com.test.hiltapplication.data.remote.DogService
import com.test.hiltapplication.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Created by Chandan Jana on 21-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton // declares scoped singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton // declares scoped singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton // declares scoped singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton // declares scoped singleton
    @Provides
    fun provideCurrencyService(retrofit: Retrofit): DogService =
        retrofit.create(DogService::class.java)

}
