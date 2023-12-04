package com.m21droid.github.presentation.screens.users

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m21droid.github.domain.models.ResponseState
import com.m21droid.github.domain.usecases.GetAllUsersUseCase
import com.m21droid.github.domain.usecases.GetSelectedUsersUseCase
import com.m21droid.github.domain.usecases.SaveSelectedUsersUseCase
import com.m21droid.github.logD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getSelectedUsersUseCase: GetSelectedUsersUseCase,
    private val saveSelectedUsersUseCase: SaveSelectedUsersUseCase,
) : ViewModel() {

    private val _usersState = mutableStateOf<UsersState>(UsersState.Empty)
    internal val usersState: State<UsersState> = _usersState

    init {
        getUsers()
    }

    private fun getUsers() {
        val flow1 = getAllUsersUseCase.execute(Unit)
        val flow2 = getSelectedUsersUseCase.execute(Unit)
        flow1.combine(flow2) { remote, local ->
            logD("getUsers: remote - $remote, local - $local")

            _usersState.value = when (remote) {
                ResponseState.Loading -> UsersState.Loading
                is ResponseState.Failure -> UsersState.Failure
                is ResponseState.Success -> {
                    val ids = when (local) {
                        ResponseState.Loading -> null
                        is ResponseState.Failure -> listOf()
                        is ResponseState.Success -> local.data
                    }
                    if (ids == null) {
                        UsersState.Loading
                    } else {
                        val data = remote.data.map { user ->
                            val value = ids.contains(user.id)
                            Pair(user, mutableStateOf(value))
                        }
                        if (data.isEmpty()) UsersState.Empty else UsersState.Display(data)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getDataFromState() = when (val state = usersState.value) {
        is UsersState.Display -> state.data
        is UsersState.DisplaySort -> state.data
        else -> null
    }

    internal fun sort() {
        getDataFromState()?.let {
            val b = _usersState.value is UsersState.Display
            _usersState.value = if (b) UsersState.DisplaySort(it) else UsersState.Display(it)
        }
    }

    internal fun select(id: Int, isSelected: Boolean) {
        getDataFromState()?.forEach {
            if (it.first.id == id) {
                it.second.value = isSelected
                return
            }
        }
    }

    override fun onCleared() {
        getDataFromState()?.mapNotNull {
            if (it.second.value) it.first else null
        }?.let {
            saveSelectedUsersUseCase.execute(it)
        }

        super.onCleared()
    }

}