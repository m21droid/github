package com.m21droid.github.data.datasources.local

import com.m21droid.github.domain.models.ResponseState
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getSelectedUsers(): Flow<ResponseState<List<Int>>>

    fun saveSelectedUsers(ids: List<Int>)

}