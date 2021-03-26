package mx.com.charlyescaz.skilltest.ui.home.contract

import mx.com.charlyescaz.skilltest.models.Test

interface MapContract {

    interface View {
        fun showProgress()

        fun hideProgress()

        fun showErrorMessage(message: String)

        fun showMarker(test: Test)
    }

    interface Presenter {

        fun getLastTest()
    }
}