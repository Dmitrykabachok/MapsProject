package com.example.firebasetest

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MarkerData::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun markerDao(): Dao
}