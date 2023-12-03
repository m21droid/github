package com.m21droid.github.presentation.screens.users

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m21droid.github.domain.models.ResponseState
import com.m21droid.github.domain.models.UserModel
import com.m21droid.github.domain.usecases.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
) : ViewModel() {

    private val _usersState = mutableStateOf<UsersState>(UsersState.Empty)
    internal val usersState: State<UsersState> = _usersState

    private var isSort = false

    init {
        getUsers()
    }

    internal fun getUsers() {
        viewModelScope.launch {
            getAllUsersUseCase.execute(Unit).collect {
                _usersState.value = when (val state = it) {
                    ResponseState.Loading -> UsersState.Loading
                    is ResponseState.Failure -> UsersState.Failure
                    is ResponseState.Success -> {
                        val data = state.data
                        if (data.isEmpty()) {
                            UsersState.Empty
                        } else {
                            if (isSort) {
                                UsersState.DisplaySort(data)
                            } else {
                                UsersState.DisplayNotSort(data)
                            }
                        }
                    }
                }
            }
        }
    }

    internal fun changeSort() {
        isSort = !isSort
        when (val state = usersState.value) {
            is UsersState.DisplayNotSort -> {
                changeState(state.users)
            }

            is UsersState.DisplaySort -> {
                changeState(state.users)
            }

            else -> {}
        }
    }

    private fun changeState(users: List<UserModel>) {
        _usersState.value =
            if (isSort) UsersState.DisplaySort(users) else UsersState.DisplayNotSort(users)
    }

    override fun onCleared() {
        super.onCleared()
    }

}