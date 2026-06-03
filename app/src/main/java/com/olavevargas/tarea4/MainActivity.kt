package com.olavevargas.tarea3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.olavevargas.tarea3.ui.model.CategoryViewModel
import com.olavevargas.tarea3.ui.model.EventViewModel
import com.olavevargas.tarea3.ui.navigation.Navigation
import com.olavevargas.tarea3.ui.theme.EventMasterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            EventMasterTheme {

                val eventViewModel: EventViewModel =
                    hiltViewModel()

                val categoryViewModel: CategoryViewModel =
                    hiltViewModel()

                Navigation(
                    viewModel = eventViewModel,
                    categoryViewModel = categoryViewModel
                )
            }
        }
    }
}