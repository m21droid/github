package com.m21droid.github.data.datasources.remote

import android.util.Log
import com.m21droid.github.data.datasources.remote.dto.UserDTO
import com.m21droid.github.data.datasources.remote.dto.UserDetailsDTO
import com.m21droid.github.data.datasources.remote.rest.Const.RESPONSE_CODE
import com.m21droid.github.data.datasources.remote.rest.RestApi
import com.m21droid.github.domain.models.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class RemoteDataSourceImpl(
    private val restApi: RestApi,
) : RemoteDataSource {

    override fun getAllUsers(): Flow<ResponseState<List<UserDTO>>> = flow {
        try {
            emit(ResponseState.Loading)

            Log.d(TAG, "getAllUsers: ")
            val execute = restApi.users()
            val code = execute.code()
            val body = execute.body() ?: listOf()
            Log.d(TAG, "getAllUsers: code - $code, body - $body")
            if (code == 200) {
                emit(ResponseState.Success(body))
            } else {
                emit(ResponseState.Failure(IOException(RESPONSE_CODE)))
            }
        } catch (e: IOException) {
            Log.e(TAG, "getAllUsers: error - ${e.message}")
            emit(ResponseState.Failure(e))
        }
    }

    override fun getUserDetails(login: String): Flow<ResponseState<UserDetailsDTO>> = flow {
        try {
            emit(ResponseState.Loading)

            Log.d(TAG, "getUserDetails: login - $login")
            val execute = restApi.user(login)
            val code = execute.code()
            val body = execute.body()
            Log.d(TAG, "getUserDetails: code - $code, body - $body")
            if (code == 200 && body != null) {
                emit(ResponseState.Success(body))
            } else {
                emit(ResponseState.Failure(IOException(RESPONSE_CODE)))
            }
        } catch (e: IOException) {
            Log.e(TAG, "getUserDetails: error - ${e.message}")
            emit(ResponseState.Failure(e))
        }
    }

    companion object {
        private val TAG = RemoteDataSourceImpl::class.simpleName
    }

}