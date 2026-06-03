package com.olavevargas.tarea4.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.olavevargas.tarea4.data.local.dao.CategoryDao
import com.olavevargas.tarea4.data.local.dao.EventDao
import com.olavevargas.tarea4.data.local.entity.Category
import com.olavevargas.tarea4.data.local.entity.Event

@Database(entities = [Category::class, Event::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
