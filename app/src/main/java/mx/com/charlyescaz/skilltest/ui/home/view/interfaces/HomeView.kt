package mx.com.charlyescaz.skilltest.ui.home.view.interfaces

interface HomeView {

    fun showProgress()

    fun hideProgress()

    fun showErrorMessage(message: String)

    fun onSuccess()
}