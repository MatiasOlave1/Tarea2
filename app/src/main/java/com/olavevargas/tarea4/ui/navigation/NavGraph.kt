package com.olavevargas.tarea4.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

import com.olavevargas.tarea4.ui.model.CategoryViewModel
import com.olavevargas.tarea4.ui.model.EventViewModel
import com.olavevargas.tarea4.ui.screens.AddEventScreen
import com.olavevargas.tarea4.ui.screens.DetailScreen
import com.olavevargas.tarea4.ui.screens.HomeScreen

@Composable
fun Navigation(
    viewModel: EventViewModel,
    categoryViewModel: CategoryViewModel
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {

        composable<Home> {

            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable<Detail> { backStackEntry ->

            val args = backStackEntry.toRoute<Detail>()

            DetailScreen(
                idCategoria = args.idCategoria,
                viewModel = viewModel,
                navController = navController
            )
        }

        composable<AddEvent> {

            AddEventScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}