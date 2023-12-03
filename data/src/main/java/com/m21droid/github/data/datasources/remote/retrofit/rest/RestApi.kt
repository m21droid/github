package com.m21droid.github.data.datasources.remote.retrofit.rest

import com.m21droid.github.data.datasources.remote.retrofit.dto.UserDTO
import com.m21droid.github.data.datasources.remote.retrofit.dto.UserDetailsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {

    @GET("users")
    suspend fun users(): Response<List<UserDTO>>

    @GET("users/{login}")
    suspend fun user(@Path("login") login: String): Response<UserDetailsDTO>

}