package com.m21droid.github.presentation.navigation

import com.m21droid.github.presentation.navigation.NavConst.ARG_DETAILS

sealed class AppRoute(val route: String) {

    object Users : AppRoute("users")

    object Details : AppRoute("details/{$ARG_DETAILS}") {
        fun route(login: String) = "details/${login}"
    }

}