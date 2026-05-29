package com.olavevargas.tarea4.data.local.dao

import androidx.room.*
import com.olavevargas.tarea4.data.local.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAll(): Flow<List<Category>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category): Long

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("SELECT * FROM categories WHERE id = :id")
    fun getById(id: Int): Flow<Category>

    @Query("SELECT * FROM categories WHERE nombre = :nombre LIMIT 1")
    suspend fun getByName(nombre: String): Category?
}
