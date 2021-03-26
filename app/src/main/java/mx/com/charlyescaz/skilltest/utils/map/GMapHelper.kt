package mx.com.charlyescaz.skilltest.utils.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class GMapHelper(private val mapHandler: MapInterface) : OnMapReadyCallback {

    lateinit var map: GoogleMap

    override fun onMapReady(gMap: GoogleMap?) {
        map = gMap!!
        mapHandler.onMapCompletelyConfigured(map)
    }

    fun setMarker(
        location: LatLng,
        title: String
    ) {
        map.clear()
        map.addMarker(
            MarkerOptions()
                .position(location)
                .title(title)
        )
    }

    fun centerPosition(position: LatLng) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15f))
    }


}