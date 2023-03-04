package com.archik.notes.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.archik.notes.models.AppNote

@Database(entities = [AppNote::class], version = 1)
abstract class AppRoomDatabase: RoomDatabase() {
  companion object {
    private var db: AppRoomDatabase? = null
    private const val DB_NAME = "main.db"
    private val LOCK = Any()

    fun getInstance(context: Context): AppRoomDatabase {
      synchronized(LOCK) {
        db?.let { return it }

        val instance = Room.databaseBuilder(context, AppRoomDatabase::class.java, DB_NAME).build()

        db = instance

        return instance
      }
    }
  }

  abstract fun appRoomDao(): AppRoomDao
}