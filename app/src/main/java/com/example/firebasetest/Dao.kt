package com.example.firebasetest

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

interface Dao {
    @Query("SELECT * FROM markerdata")
    fun getAll(): List<MarkerData>

    @Insert
    fun insertAll(vararg users: MarkerData)

    @Delete
    fun delete(user: MarkerData)

}