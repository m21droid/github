package com.m21droid.github.data.repositories

import android.util.Log
import com.m21droid.github.data.datasources.remote.RemoteDataSource
import com.m21droid.github.data.datasources.remote.mappers.toUserModel
import com.m21droid.github.domain.models.ResponseState
import com.m21droid.github.domain.models.UserDetailsModel
import com.m21droid.github.domain.models.UserModel
import com.m21droid.github.domain.repositories.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DataRepository {

    override fun getAllUsers(): Flow<ResponseState<List<UserModel>>> {
        Log.i(TAG, "getAllBooks: ")

        return remoteDataSource.getUsers().map { state ->
            when (state) {
                ResponseState.Loading -> {
                    ResponseState.Loading
                }

                is ResponseState.Failure -> {
                    ResponseState.Failure(state.throwable)
                }

                is ResponseState.Success -> {
                    val list = state.data.map {
                        it.toUserModel()
                    }
                    ResponseState.Success(list)
                }
            }
        }

    }

    override fun getUser(login: String): Flow<ResponseState<UserDetailsModel>> {
        Log.i(TAG, "getUser: login - $login")

        return remoteDataSource.getUser(login).map { state ->
            when (state) {
                ResponseState.Loading -> {
                    ResponseState.Loading
                }

                is ResponseState.Failure -> {
                    ResponseState.Failure(state.throwable)
                }

                is ResponseState.Success -> {
                    val user = state.data.toUserModel()
                    ResponseState.Success(user)
                }
            }
        }
    }

    companion object {
        private val TAG = DataRepositoryImpl::class.simpleName
    }

}