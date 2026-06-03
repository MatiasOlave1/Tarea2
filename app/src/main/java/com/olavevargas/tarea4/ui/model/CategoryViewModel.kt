package com.olavevargas.tarea3.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olavevargas.tarea3.data.local.entity.Category
import com.olavevargas.tarea3.data.repository.category.CategoryRepository
import com.olavevargas.tarea3.ui.state.UiState
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
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
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

    fun addCategory(nombre: String) {

        viewModelScope.launch {
            try {
                categoryRepository.insertCategory(
                    Category(
                        id = 0,
                        nombre = nombre
                    )
                )
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    suspend fun getOrAddCategory(
        nombre: String
    ): Int {
        return try {
            val state = categoriesUiState.value
            val existing = if (state is UiState.Success) {
                state.data.find {
                    it.nombre.equals(nombre, ignoreCase = true)
                }
            } else {
                null
            }

            if (existing != null) {
                return existing.id
            }

            val newCategory = Category(
                id = 0,
                nombre = nombre
            )

            categoryRepository.insertCategory(newCategory)
        } catch (e: Exception) {
            0
        }
    }
}