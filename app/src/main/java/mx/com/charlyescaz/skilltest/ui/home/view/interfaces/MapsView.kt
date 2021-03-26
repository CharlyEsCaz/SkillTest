package mx.com.charlyescaz.skilltest.ui.home.view.interfaces

import mx.com.charlyescaz.skilltest.models.Test

interface MapsView {

    fun showProgress()

    fun hideProgress()

    fun showErrorMessage(message: String)

    fun showMarker(test: Test)
}