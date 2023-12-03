package com.m21droid.github.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.m21droid.github.presentation.screens.details.detailsGraph
import com.m21droid.github.presentation.screens.users.usersGraph

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppRoute.Users.route,
    ) {
        detailsGraph(navigation = navController)
        usersGraph(navigation = navController)
    }
}