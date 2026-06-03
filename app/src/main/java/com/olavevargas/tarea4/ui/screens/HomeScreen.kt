package com.olavevargas.tarea4.ui.screens
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.olavevargas.tarea4.ui.model.EventViewModel
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import com.olavevargas.tarea4.R
import com.olavevargas.tarea4.ui.navigation.AddEvent
import com.olavevargas.tarea4.ui.navigation.Detail

import androidx.compose.ui.Alignment
import com.olavevargas.tarea3.ui.state.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: EventViewModel,
    navController: NavController
)  {

    val categoriesState by viewModel.categoriesUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                    title = { Text(stringResource(R.string.home_title)) },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.logo_main_activity),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(end = 16.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(AddEvent)
                }
            ) {
                Text("+")
            }
        }

    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (val state = categoriesState) {
                is UiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        items(state.data){ categoria ->

                            CategoryItem(
                                nombre = categoria.nombre,
                                onClick = {

                                    navController.navigate(Detail(categoria.id))
                                }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
                is UiState.Error -> {
                    Text(
                        text = "Error: ${state.message}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
@Composable
fun CategoryItem(
    nombre: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Text(
            text = nombre,
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}