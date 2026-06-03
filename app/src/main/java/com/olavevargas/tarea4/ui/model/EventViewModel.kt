package com.olavevargas.tarea4.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olavevargas.tarea4.data.local.entity.Category
import com.olavevargas.tarea4.data.local.entity.Event
import com.olavevargas.tarea4.data.repository.Event.EventRepository
import com.olavevargas.tarea4.data.repository.category.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val eventRepository: EventRepository
) : ViewModel() {

    val categoriesUiState: StateFlow<List<Category>> =
        categoryRepository.getAllCategoriesStream()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = emptyList()
            )

    val eventsUiState: StateFlow<List<Event>> =
        eventRepository.getAllEventsStream()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = emptyList()
            )

    suspend fun addCategory(nombre: String): Int {

        val existingCategory =
            categoryRepository.getCategoryByName(nombre)

        return existingCategory?.id
            ?: categoryRepository.insertCategory(
                Category(
                    id = 0,
                    nombre = nombre
                )
            )
    }

    fun addEvent(
        titulo: String,
        descripcion: String,
        idCategoria: Int
    ) {

        viewModelScope.launch {

            eventRepository.insertEvent(
                Event(
                    id = 0,
                    titulo = titulo,
                    descripcion = descripcion,
                    idCategoria = idCategoria
                )
            )
        }
    }

    fun getEventsByCategory(
        idCategoria: Int
    ): List<Event> {

        return eventsUiState.value.filter {
            it.idCategoria == idCategoria
        }
    }

    fun getEventById(
        id: Int
    ): Event? {

        return eventsUiState.value.find {
            it.id == id
        }
    }

    fun getCategoryById(
        id: Int
    ): Category? {

        return categoriesUiState.value.find {
            it.id == id
        }
    }
}