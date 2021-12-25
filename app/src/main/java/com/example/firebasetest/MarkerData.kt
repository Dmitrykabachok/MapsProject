package com.example.firebasetest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

@Entity
data class MarkerData(
    @PrimaryKey val mid: Int,
    @ColumnInfo(name = "marker_name") val  marker_name: String?,
    @ColumnInfo(name = "marker_info") val marker_info: String?,
    @ColumnInfo(name = "marker_lat") val marker_lat: Double,
    @ColumnInfo(name = "marker_lng") val marker_lng: Double,

)
