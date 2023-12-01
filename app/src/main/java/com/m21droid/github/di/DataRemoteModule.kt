package com.m21droid.github.di

import android.content.Context
import android.provider.Settings.Secure.*
import com.m21droid.github.data.datasources.remote.RemoteDataSource
import com.m21droid.github.data.datasources.remote.RemoteDataSourceImpl
import com.m21droid.github.data.datasources.remote.rest.RestApi
import com.m21droid.github.data.datasources.remote.rest.RestClient
import com.m21droid.github.data.datasources.remote.rest.RestModule
import com.m21droid.github.data.repositories.DataRepositoryImpl
import com.m21droid.github.domain.repositories.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataRemoteModule {

    @Singleton
    @Provides
    fun provideRestClient(@ApplicationContext context: Context): OkHttpClient =
        RestClient(context).okHttpClient

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        RestModule(okHttpClient).retrofit

    @Singleton
    @Provides
    fun provideRestApi(retrofit: Retrofit): RestApi =
        retrofit.create(RestApi::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(restApi: RestApi): RemoteDataSource {
        return RemoteDataSourceImpl(restApi)
    }

    @Provides
    @Singleton
    fun provideDataRepository(remoteDataSource: RemoteDataSource): DataRepository {
        return DataRepositoryImpl(remoteDataSource)
    }

}