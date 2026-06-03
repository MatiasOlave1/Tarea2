package com.olavevargas.tarea4.data.local.dao

import androidx.room.*
import com.olavevargas.tarea4.data.local.entity.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM events")
    fun getAll(): Flow<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Update
    suspend fun update(event: Event)

    @Delete
    suspend fun delete(event: Event)

    @Query("SELECT * FROM events WHERE idCategoria = :categoryId")
    fun getByCategoryId(categoryId: Int): Flow<List<Event>>
}
