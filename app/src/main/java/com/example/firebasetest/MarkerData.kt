package com.example.firebasetest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MarkerData(
    @PrimaryKey val mid: Int,
    @ColumnInfo(name = "marker_name") val  marker_name: String?,
    @ColumnInfo(name = "marker_info") val marker_info: String?
)
