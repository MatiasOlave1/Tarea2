package com.olavevargas.tarea4.data.repository.Event

import com.olavevargas.tarea4.data.local.dao.EventDao
import com.olavevargas.tarea4.data.local.entity.Event
import kotlinx.coroutines.flow.Flow

class EventRepositoryImpl(private val eventDao: EventDao) : EventRepository {
    override fun getAllEventsStream(): Flow<List<Event>> = eventDao.getAll()

    override fun getEventsByCategoryStream(categoryId: Int): Flow<List<Event>> = eventDao.getByCategoryId(categoryId)

    override suspend fun insertEvent(event: Event) = eventDao.insert(event)

    override suspend fun deleteEvent(event: Event) = eventDao.delete(event)

    override suspend fun updateEvent(event: Event) = eventDao.update(event)
}
