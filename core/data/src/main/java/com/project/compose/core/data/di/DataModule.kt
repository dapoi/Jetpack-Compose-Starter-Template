package com.project.compose.core.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.project.compose.core.data.BuildConfig.DEBUG
import com.project.compose.core.data.repository.AppRepository
import com.project.compose.core.data.repository.AppRepositoryImpl
import com.project.compose.core.data.source.local.db.datastore.AppDataStore
import com.project.compose.core.data.source.remote.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideRepository(apiService: ApiService): AppRepository = AppRepositoryImpl(apiService)

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl("")
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()

    @Provides
    fun provideOkHttpClient(
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(if (DEBUG) BODY else NONE))
        .addInterceptor(chuckerInterceptor)
        .build()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideChuckerInterceptor(
        @ApplicationContext context: Context
    ) = ChuckerInterceptor.Builder(context).collector(ChuckerCollector(context)).build()

    @Provides
    fun provideDataStore(@ApplicationContext context: Context) = AppDataStore(context)
}