package mx.com.charlyescaz.skilltest.ui.details.view.interfaces


import mx.com.charlyescaz.skilltest.models.Test
import mx.com.charlyescaz.skilltest.models.Weather

interface OtherDetailsView {

    fun showProgress()

    fun hideProgress()

    fun showErrorMessage(message: String)

    fun onSuccess(test: Test, weathers: List<Weather>)
}