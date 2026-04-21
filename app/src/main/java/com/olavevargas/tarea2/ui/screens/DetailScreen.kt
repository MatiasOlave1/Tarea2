package com.olavevargas.tarea2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.olavevargas.tarea2.viewmodel.EventViewModel
import com.olavevargas.tarea2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    categoryId: Int,
    viewModel: EventViewModel,
    navController: NavController
) {

    val events = viewModel.getEventsByCategory(categoryId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(stringResource(R.string.detail_title)) }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            items(events) { event ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = event.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = event.description)
                    }
                }
            }
        }
    }
}