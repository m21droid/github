package com.m21droid.github.presentation.screens.users

import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.m21droid.github.domain.models.UserModel
import com.m21droid.github.presentation.navigation.AppRoute
import com.m21droid.github.presentation.navigation.NavConst.ARG_ID
import com.m21droid.github.presentation.navigation.NavConst.ARG_SELECTED
import com.m21droid.github.take

fun NavGraphBuilder.usersGraph(navigation: NavHostController) =
    composable(route = AppRoute.Users.route) {
        val viewModel = hiltViewModel<UsersViewModel>()

        navigation.currentBackStackEntry?.take<Pair<Int, Boolean>>(ARG_ID)?.apply {
            viewModel.select(first, second)
        }

        val onClickSort: () -> Unit = {
            viewModel.sort()
        }

        val onClickItem: (Pair<UserModel, MutableState<Boolean>>) -> Unit = {
            navigation.currentBackStackEntry?.savedStateHandle?.set(ARG_SELECTED, it.second.value)
            val route = AppRoute.Details.route(it.first.login)
            navigation.navigate(route)
        }

        UsersScreen(
            state = viewModel.usersState,
            onClickSort = onClickSort,
            onClickItem = onClickItem
        )
    }