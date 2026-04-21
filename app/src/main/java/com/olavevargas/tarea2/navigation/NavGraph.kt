package com.olavevargas.tarea2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.olavevargas.tarea2.ui.screens.HomeScreen
import com.olavevargas.tarea2.viewmodel.EventViewModel

@Composable
fun NavGraph(viewModel: EventViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

    }
}