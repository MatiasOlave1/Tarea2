package com.olavevargas.tarea4.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olavevargas.tarea4.data.local.entity.Category
import com.olavevargas.tarea4.data.local.entity.Event
import com.olavevargas.tarea4.data.repository.Event.EventRepository
import com.olavevargas.tarea4.data.repository.category.CategoryRepository
import com.olavevargas.tarea4.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val eventRepository: EventRepository
) : ViewModel() {

    val categoriesUiState: StateFlow<UiState<List<Category>>> =
        categoryRepository.getAllCategoriesStream()
            .map { UiState.Success(it) as UiState<List<Category>> }
            .onStart { emit(UiState.Loading) }
            .catch { emit(UiState.Error(it.message ?: "Error desconocido")) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = UiState.Loading
            )

    val eventsUiState: StateFlow<UiState<List<Event>>> =
        eventRepository.getAllEventsStream()
            .map { UiState.Success(it) as UiState<List<Event>> }
            .onStart { emit(UiState.Loading) }
            .catch { emit(UiState.Error(it.message ?: "Error desconocido")) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = UiState.Loading
            )

    suspend fun addCategory(nombre: String): Int {
        return try {
            val existingCategory =
                categoryRepository.getCategoryByName(nombre)

            existingCategory?.id
                ?: categoryRepository.insertCategory(
                    Category(
                        id = 0,
                        nombre = nombre
                    )
                )
        } catch (e: Exception) {
            0
        }
    }

    fun addEvent(
        titulo: String,
        descripcion: String,
        idCategoria: Int
    ) {
        viewModelScope.launch {
            try {
                eventRepository.insertEvent(
                    Event(
                        id = 0,
                        titulo = titulo,
                        descripcion = descripcion,
                        idCategoria = idCategoria
                    )
                )
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun getEventsByCategory(
        idCategoria: Int
    ): List<Event> {
        val state = eventsUiState.value
        return if (state is UiState.Success) {
            state.data.filter { it.idCategoria == idCategoria }
        } else {
            emptyList()
        }
    }

    fun getEventById(
        id: Int
    ): Event? {
        val state = eventsUiState.value
        return if (state is UiState.Success) {
            state.data.find { it.id == id }
        } else {
            null
        }
    }

    fun getCategoryById(
        id: Int
    ): Category? {
        val state = categoriesUiState.value
        return if (state is UiState.Success) {
            state.data.find { it.id == id }
        } else {
            null
        }
    }
}