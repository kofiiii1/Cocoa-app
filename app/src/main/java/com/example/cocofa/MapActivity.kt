package com.example.cocofa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity() {
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { googleMap ->
            googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            val markerOptions = MarkerOptions().position(LatLng(5.6037,  -0.1870)).title("Fedco, Accra")
            googleMap.addMarker(markerOptions)

            val cameraPosition = CameraPosition.Builder().target(LatLng(5.6037,  -0.1870)).zoom(12f).build()
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

            val markerOptions2 = MarkerOptions().position(LatLng(6.6885,  -1.6244)).title("Golden Tree, Kumasi")
            googleMap.addMarker(markerOptions2)

            val cameraPosition2 = CameraPosition.Builder().target(LatLng(6.6885,  -1.6244)).zoom(12f).build()
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition2))

            val markerOptions3 = MarkerOptions().position(LatLng(5.6404,  -0.0101)).title("Steed, Tema")
            googleMap.addMarker(markerOptions3)

            val cameraPosition3 = CameraPosition.Builder().target(LatLng(5.6404,  -0.0101)).zoom(12f).build()
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition3))

        }


    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }


}

