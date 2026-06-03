package com.olavevargas.tarea4.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olavevargas.tarea4.data.local.entity.Category
import com.olavevargas.tarea4.data.repository.category.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    val categoriesUiState: StateFlow<List<Category>> =
        categoryRepository.getAllCategoriesStream()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = emptyList()
            )

    fun addCategory(nombre: String) {

        viewModelScope.launch {

            categoryRepository.insertCategory(
                Category(
                    id = 0,
                    nombre = nombre
                )
            )
        }
    }

    suspend fun getOrAddCategory(
        nombre: String
    ): Int {

        val existing =
            categoriesUiState.value.find {
                it.nombre.equals(nombre, ignoreCase = true)
            }

        if (existing != null) {
            return existing.id
        }

        val newCategory = Category(
            id = 0,
            nombre = nombre
        )

        return categoryRepository.insertCategory(newCategory)
    }
}