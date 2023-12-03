package com.m21droid.github.data.datasources.remote.rest

import com.m21droid.github.data.datasources.remote.dto.UserDTO
import com.m21droid.github.data.datasources.remote.dto.UserDetailsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("users")
    suspend fun users(): Response<List<UserDTO>>

    @GET("users/{login}")
    suspend fun user(@Query("login") login: String): Response<UserDetailsDTO>

}