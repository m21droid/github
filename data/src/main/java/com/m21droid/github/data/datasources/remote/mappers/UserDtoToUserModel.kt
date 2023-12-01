package com.m21droid.github.data.datasources.remote.mappers

import com.m21droid.github.data.datasources.remote.dto.UserDTO
import com.m21droid.github.domain.models.UserModel

fun UserDTO.toUserModel(): UserModel {
    return UserModel(
        login = login ?: "",
        id = id ?: 0,
        avatarUrl = avatarUrl ?: ""
    )
}