package com.m21droid.github.presentation.screens.details

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.m21droid.github.presentation.navigation.AppRoute
import com.m21droid.github.presentation.navigation.NavConst.ARG_DETAILS

fun NavGraphBuilder.detailsGraph(navigation: NavHostController) =
    composable(
        route = AppRoute.Details.route,
        arguments = listOf(navArgument(ARG_DETAILS) { type = NavType.StringType })
    ) {
        val viewModel = hiltViewModel<DetailsViewModel>()

        DetailsScreen(state = viewModel.detailsState)
    }