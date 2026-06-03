package com.olavevargas.tarea4.data.repository.category

import com.olavevargas.tarea4.data.local.dao.CategoryDao
import com.olavevargas.tarea4.data.local.entity.Category
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(private val categoryDao: CategoryDao) : CategoryRepository {
    override fun getAllCategoriesStream(): Flow<List<Category>> = categoryDao.getAll()

    override fun getCategoryStream(id: Int): Flow<Category?> = categoryDao.getById(id)

    override suspend fun insertCategory(category: Category): Int = categoryDao.insert(category).toInt()

    override suspend fun getCategoryByName(nombre: String): Category? = categoryDao.getByName(nombre)

    override suspend fun deleteCategory(category: Category) = categoryDao.delete(category)

    override suspend fun updateCategory(category: Category) = categoryDao.update(category)
}
