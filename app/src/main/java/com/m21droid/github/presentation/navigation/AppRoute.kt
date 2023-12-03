package com.m21droid.github.presentation.navigation

import com.m21droid.github.Const.ARG_USER_PATH

sealed class AppRoute(val route: String) {

    object Users : AppRoute("all_users")

    object NewWords : AppRoute("user_details/{$ARG_USER_PATH}") {
        fun route(login: String) = "user_details/${login}"
    }

}