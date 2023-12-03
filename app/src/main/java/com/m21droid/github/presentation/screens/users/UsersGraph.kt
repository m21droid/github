package com.m21droid.github.presentation.screens.users

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.m21droid.github.domain.models.UserModel
import com.m21droid.github.presentation.navigation.AppRoute

fun NavGraphBuilder.usersGraph(navigation: NavHostController) =
    composable(route = AppRoute.Users.route) {
        val viewModel = hiltViewModel<UsersViewModel>()

        val onClickSort = {
            viewModel.sort()
        }

        val onClickItem = { user: UserModel ->
            val route = AppRoute.NewWords.route(user.login)
            navigation.navigate(route)
        }

        UsersScreen(
            state = viewModel.usersState,
            onClickSort = onClickSort,
            onClickItem = onClickItem
        )
    }