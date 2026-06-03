package com.olavevargas.tarea3.data.remote.repository

import com.olavevargas.tarea3.data.local.entity.Category
import com.olavevargas.tarea3.data.remote.EventMasterApi
import com.olavevargas.tarea3.data.remote.dto.toDto
import com.olavevargas.tarea3.data.remote.dto.toEntity
import com.olavevargas.tarea3.data.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRemoteRepositoryImpl @Inject constructor(
    private val api: EventMasterApi
) : CategoryRepository {

    override fun getAllCategoriesStream(): Flow<List<Category>> = flow {
        val categories = api.getCategories().map { it.toEntity() }
        emit(categories)
    }

    override fun getCategoryStream(id: Int): Flow<Category?> = flow {
        val category = api.getCategory(id).toEntity()
        emit(category)
    }

    override suspend fun insertCategory(category: Category): Int {
        val response = api.createCategory(category.toDto())
        return response.id ?: 0
    }

    override suspend fun getCategoryByName(nombre: String): Category? {
        // The API might not have this specific endpoint, but we can filter locally or just return null for now
        // if the requirement is to check for duplicates.
        return api.getCategories().find { it.nombre == nombre }?.toEntity()
    }

    override suspend fun deleteCategory(category: Category) {
        // TODO: Implement delete in API if necessary. Pauta only mentions "gestionar".
    }

    override suspend fun updateCategory(category: Category) {
        // TODO: Implement update in API if necessary.
    }
}