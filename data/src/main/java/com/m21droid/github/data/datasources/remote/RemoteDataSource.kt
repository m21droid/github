package com.m21droid.github.data.datasources.remote

import com.m21droid.github.data.datasources.remote.dto.UserDTO
import com.m21droid.github.data.datasources.remote.dto.UserDetailsDTO
import com.m21droid.github.domain.models.ResponseState
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllUsers(): Flow<ResponseState<List<UserDTO>>>

    fun getUserDetails(login: String): Flow<ResponseState<UserDetailsDTO>>

}