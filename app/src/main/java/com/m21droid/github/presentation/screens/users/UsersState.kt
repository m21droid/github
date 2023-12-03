package com.m21droid.github.presentation.screens.users

import androidx.compose.runtime.MutableState
import com.m21droid.github.domain.models.UserModel

typealias UsersStateData = List<Pair<UserModel, MutableState<Boolean>>>

sealed class UsersState {

    object Loading : UsersState()

    object Failure : UsersState()

    object Empty : UsersState()

    data class Display(val data: UsersStateData) : UsersState()

    data class DisplaySort(val data: UsersStateData) : UsersState()

}