package com.m21droid.github.data.datasources.remote.retrofit.rest

import com.m21droid.github.data.datasources.remote.retrofit.rest.Const.ENDPOINT
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestModule(okHttpClient: OkHttpClient) {

    val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}