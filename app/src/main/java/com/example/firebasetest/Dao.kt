package com.example.firebasetest

import androidx.annotation.NonNull
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.jetbrains.annotations.NotNull

@Dao
interface Dao {
    @Query("SELECT * FROM markerdata")
    suspend fun getAll(): List<MarkerData>?

    @Insert

    suspend fun insert(marker: MarkerData?)

    @Delete
    suspend fun delete(marker: MarkerData?)

}