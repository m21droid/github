package com.m21droid.github.presentation.screens.details

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.m21droid.github.presentation.navigation.AppRoute
import com.m21droid.github.presentation.navigation.NavConst.ARG_ID
import com.m21droid.github.presentation.navigation.NavConst.ARG_LOGIN
import com.m21droid.github.presentation.navigation.NavConst.ARG_SELECTED

fun NavGraphBuilder.detailsGraph(navigation: NavHostController) =
    composable(
        route = AppRoute.Details.route,
        arguments = listOf(navArgument(ARG_LOGIN) { type = NavType.StringType })
    ) {
        val viewModel = hiltViewModel<DetailsViewModel>()

        navigation.previousBackStackEntry?.savedStateHandle?.apply {
            get<Boolean>(ARG_SELECTED)?.let {
                remove<Boolean>(ARG_SELECTED)
                viewModel.isSelected = it
            }
        }

        val onClickBack: (DetailsStateData?) -> Unit = {
            it?.apply {
                navigation.previousBackStackEntry?.savedStateHandle?.apply {
                    set(ARG_ID, Pair(first.id, second.value))
                }
            }
            navigation.popBackStack()
        }

        DetailsScreen(state = viewModel.detailsState, onClickBack = onClickBack)
    }