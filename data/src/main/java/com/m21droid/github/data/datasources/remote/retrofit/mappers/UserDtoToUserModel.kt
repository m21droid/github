package com.m21droid.github.data.datasources.remote.retrofit.mappers

import com.m21droid.github.data.datasources.remote.retrofit.dto.UserDTO
import com.m21droid.github.domain.models.UserModel

fun UserDTO.toUserModel(): UserModel {
    return UserModel(
        login = login ?: "",
        id = id ?: 0,
        avatarUrl = avatarUrl ?: ""
    )
}