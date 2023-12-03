package com.m21droid.github.presentation.navigation

import com.m21droid.github.presentation.navigation.NavConst.ARG_LOGIN

sealed class AppRoute(val route: String) {

    object Users : AppRoute("users")

    object Details : AppRoute("users/{$ARG_LOGIN}") {
        fun route(login: String) = "users/${login}"
    }

}