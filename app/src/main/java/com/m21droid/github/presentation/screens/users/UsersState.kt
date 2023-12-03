package com.m21droid.github.presentation.screens.users

import com.m21droid.github.domain.models.UserModel

sealed class UsersState {

    object Loading : UsersState()

    object Failure : UsersState()

    object Empty : UsersState()

    data class DisplayNotSort(val users: List<UserModel>) : UsersState()

    data class DisplaySort(val users: List<UserModel>) : UsersState()

}