package com.m21droid.github.data.datasources.local.sharedpreferences

import android.content.SharedPreferences
import android.util.Log
import com.m21droid.github.data.datasources.local.LocalDataSource
import com.m21droid.github.domain.models.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSourceImpl(
    private val preferences: SharedPreferences,
) : LocalDataSource {

    override fun getSelectedUsers(): Flow<ResponseState<List<Int>>> = flow {
        Log.d(TAG, "getSelectedUsers: ")

        emit(ResponseState.Loading)

        val data = preferences.getStringSet(PREF_IDS, setOf())?.map {
            it.toIntOrNull() ?: -1
        } ?: listOf()
        emit(ResponseState.Success(data))
    }

    override fun saveSelectedUsers(ids: List<Int>) {
        Log.d(TAG, "saveSelectedUsers: ids - $ids")

        val set = ids.map { it.toString() }.toSet()
        preferences.edit().putStringSet(PREF_IDS, set).apply()
    }

    companion object {
        private val TAG = LocalDataSourceImpl::class.simpleName

        const val PREF_IDS = "pref_ids"
    }

}