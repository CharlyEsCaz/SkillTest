package mx.com.charlyescaz.skilltest.ui.home.view.interfaces

import mx.com.charlyescaz.skilltest.models.Test

interface OtherView {

    fun showProgress()

    fun hideProgress()

    fun showErrorMessage(message: String)

    fun onEmptyList()

    fun list(tests: List<Test>)
}