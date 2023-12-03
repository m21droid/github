package com.m21droid.github.data.datasources.remote.retrofit.mappers

import com.m21droid.github.data.datasources.remote.retrofit.dto.UserDetailsDTO
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