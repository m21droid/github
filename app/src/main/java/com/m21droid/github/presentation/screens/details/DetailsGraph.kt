package com.m21droid.github.presentation.screens.details

import androidx.activity.compose.BackHandler
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
import com.m21droid.github.take

fun NavGraphBuilder.detailsGraph(navigation: NavHostController) =
    composable(
        route = AppRoute.Details.route,
        arguments = listOf(navArgument(ARG_LOGIN) { type = NavType.StringType })
    ) {
        val viewModel = hiltViewModel<DetailsViewModel>()

        navigation.previousBackStackEntry?.take<Boolean>(ARG_SELECTED)?.let {
            viewModel.isSelected = it
        }

        val onClickBack: () -> Unit = {
            (viewModel.detailsState.value as? DetailsState.Display)?.data?.apply {
                navigation.previousBackStackEntry?.savedStateHandle?.apply {
                    set(ARG_ID, Pair(first.id, second.value))
                }
            }
            navigation.popBackStack()
        }

        BackHandler {
            onClickBack()
        }

        DetailsScreen(state = viewModel.detailsState, onClickBack = onClickBack)
    }