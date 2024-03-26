package com.example.petcentertwo.presenter.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.petcentertwo.presenter.data.db.dao.RegisterPetDao
import com.example.petcentertwo.presenter.data.db.entity.RegisterPetEntity

@Database(entities = [RegisterPetEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun registerPetDao(): RegisterPetDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}