package com.m21droid.github.domain.models

data class UserDetailsModel(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val name: String,
    val location: String,
)