package com.olavevargas.tarea4.di

import com.olavevargas.tarea4.data.remote.EventMasterApi
import com.olavevargas.tarea4.data.remote.repository.CategoryRemoteRepositoryImpl
import com.olavevargas.tarea4.data.remote.repository.EventRemoteRepositoryImpl
import com.olavevargas.tarea4.data.repository.Event.EventRepository
import com.olavevargas.tarea4.data.repository.category.CategoryRepository
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
        api: EventMasterApi
    ): EventRepository {

        return EventRemoteRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(
        api: EventMasterApi
    ): CategoryRepository {

        return CategoryRemoteRepositoryImpl(api)
    }
}