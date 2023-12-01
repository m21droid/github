package com.m21droid.github.data.datasources.remote.rest

import com.m21droid.github.data.datasources.remote.dto.UserDTO
import com.m21droid.github.data.datasources.remote.dto.UserDetailsDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("users")
    fun users(): Call<List<UserDTO>>

    @GET("users/{login}")
    fun user(@Query("login") login: String): Call<UserDetailsDTO>

}