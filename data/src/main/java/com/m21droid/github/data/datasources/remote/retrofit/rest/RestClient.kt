package com.m21droid.github.data.datasources.remote.retrofit.rest

import android.content.Context
import com.m21droid.github.data.BuildConfig.DEBUG
import com.m21droid.github.data.datasources.remote.retrofit.rest.Const.HTTP_CACHE
import com.m21droid.github.data.datasources.remote.retrofit.rest.Const.MAX_SIZE
import com.m21droid.github.data.datasources.remote.retrofit.rest.Const.TIMEOUT
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit.SECONDS

class RestClient(private val context: Context) {

    val okHttpClient: OkHttpClient =
        OkHttpClient.Builder().apply {
            val file = File(context.cacheDir, HTTP_CACHE)
            val cache = Cache(file, MAX_SIZE.toLong())
            cache(cache)
            readTimeout(TIMEOUT, SECONDS)
            if (DEBUG) {
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(this)
                }
            }
        }.build()

}