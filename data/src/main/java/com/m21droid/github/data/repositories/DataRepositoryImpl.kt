package com.m21droid.github.data.repositories

import android.util.Log
import com.m21droid.github.data.datasources.local.LocalDataSource
import com.m21droid.github.data.datasources.remote.RemoteDataSource
import com.m21droid.github.data.datasources.remote.retrofit.mappers.toUserModel
import com.m21droid.github.domain.models.ResponseState
import com.m21droid.github.domain.models.UserDetailsModel
import com.m21droid.github.domain.models.UserModel
import com.m21droid.github.domain.repositories.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : DataRepository {

    override fun getAllUsers(): Flow<ResponseState<List<UserModel>>> {
        Log.i(TAG, "getAllUsers: ")

        return remoteDataSource.getAllUsers().map { state ->
            when (state) {
                ResponseState.Loading -> ResponseState.Loading
                is ResponseState.Failure -> ResponseState.Failure(state.throwable)
                is ResponseState.Success -> {
                    val list = state.data.map {
                        it.toUserModel()
                    }
                    ResponseState.Success(list)
                }
            }
        }

    }

    override fun getUserDetails(login: String): Flow<ResponseState<UserDetailsModel>> {
        Log.i(TAG, "getUserDetails: login - $login")

        return remoteDataSource.getUserDetails(login).map { state ->
            when (state) {
                ResponseState.Loading -> ResponseState.Loading
                is ResponseState.Failure -> ResponseState.Failure(state.throwable)
                is ResponseState.Success -> {
                    val user = state.data.toUserModel()
                    ResponseState.Success(user)
                }
            }
        }
    }

    override fun getSelectedUsers(): Flow<ResponseState<List<Int>>> {
        Log.i(TAG, "getSelectedUsers: ")

        return localDataSource.getSelectedUsers()
    }

    override fun saveSelectedUsers(users: List<UserModel>) {
        Log.i(TAG, "saveSelectedUsers: users - $users")

        users.map { it.id }.apply {
            localDataSource.saveSelectedUsers(this)
        }
    }

    companion object {
        private val TAG = DataRepositoryImpl::class.simpleName
    }

}