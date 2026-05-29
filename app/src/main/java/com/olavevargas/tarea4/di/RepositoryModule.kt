package com.olavevargas.tarea4.di

import com.olavevargas.tarea4.data.local.dao.CategoryDao
import com.olavevargas.tarea4.data.local.dao.EventDao
import com.olavevargas.tarea4.data.repository.Event.EventRepository
import com.olavevargas.tarea4.data.repository.Event.EventRepositoryImpl
import com.olavevargas.tarea4.data.repository.category.CategoryRepository
import com.olavevargas.tarea4.data.repository.category.CategoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideEventRepository(
        eventDao: EventDao
    ): EventRepository {

        return EventRepositoryImpl(eventDao)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(
        categoryDao: CategoryDao
    ): CategoryRepository {

        return CategoryRepositoryImpl(categoryDao)
    }
}