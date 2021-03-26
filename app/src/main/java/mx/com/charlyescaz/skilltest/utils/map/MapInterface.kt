package mx.com.charlyescaz.skilltest.utils.map

import com.google.android.gms.maps.GoogleMap

interface MapInterface {
    fun onMapCompletelyConfigured(gMap: GoogleMap?)
}