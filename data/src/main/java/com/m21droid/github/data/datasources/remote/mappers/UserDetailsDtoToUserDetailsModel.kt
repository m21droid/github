package com.m21droid.github.data.datasources.remote.mappers

import com.m21droid.github.data.datasources.remote.dto.UserDetailsDTO
import com.m21droid.github.domain.models.UserDetailsModel

fun UserDetailsDTO.toUserModel(): UserDetailsModel {
    return UserDetailsModel(
        login = login ?: "",
        id = id ?: 0,
        avatarUrl = avatarUrl ?: "",
        name = name ?: "",
        location = location ?: ""
    )
}