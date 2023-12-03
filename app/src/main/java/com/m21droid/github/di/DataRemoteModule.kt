package com.m21droid.github.di

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Secure.*
import com.m21droid.github.data.datasources.local.LocalDataSource
import com.m21droid.github.data.datasources.local.sharedpreferences.LocalDataSourceImpl
import com.m21droid.github.data.datasources.remote.RemoteDataSource
import com.m21droid.github.data.datasources.remote.retrofit.RemoteDataSourceImpl
import com.m21droid.github.data.datasources.remote.retrofit.rest.RestApi
import com.m21droid.github.data.datasources.remote.retrofit.rest.RestClient
import com.m21droid.github.data.datasources.remote.retrofit.rest.RestModule
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

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("pref", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideLocalDataSource(preferences: SharedPreferences): LocalDataSource {
        return LocalDataSourceImpl(preferences)
    }

    @Provides
    @Singleton
    fun provideDataRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
    ): DataRepository {
        return DataRepositoryImpl(remoteDataSource, localDataSource)
    }

}