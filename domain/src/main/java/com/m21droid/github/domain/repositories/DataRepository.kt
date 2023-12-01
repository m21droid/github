package com.m21droid.github.domain.repositories

import com.m21droid.github.domain.models.ResponseState
import com.m21droid.github.domain.models.UserDetailsModel
import com.m21droid.github.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    fun getAllUsers(): Flow<ResponseState<List<UserModel>>>

    fun getUser(login: String): Flow<ResponseState<UserDetailsModel>>

}