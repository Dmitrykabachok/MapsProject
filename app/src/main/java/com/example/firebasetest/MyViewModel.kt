package com.example.firebasetest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MyViewModel : ViewModel() {
    lateinit var md: MarkerData
    private val markers: MutableLiveData<List<MarkerData>> by lazy {
        MutableLiveData<List<MarkerData>>().also {
            loadMarkers()
        }
    }

    fun loadMarkers() {}

     fun saveMarkerToDatabase(marker: MarkerOptions, db: AppDatabase, counter: Int) {
        runBlocking {
            launch {
                md = MarkerData(
                    counter, marker.title, marker.snippet,
                    marker.position.latitude, marker.position.longitude
                )
                db.markerDao().insert(md)

            }
        }

    }

}