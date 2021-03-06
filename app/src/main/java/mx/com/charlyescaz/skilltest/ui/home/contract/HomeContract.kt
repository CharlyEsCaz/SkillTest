package mx.com.charlyescaz.skilltest.ui.home.contract

import mx.com.charlyescaz.skilltest.models.Test

interface HomeContract {

    interface View {
        fun showProgress()

        fun hideProgress()

        fun showErrorMessage()

        fun showErrorStoreMessage()

        fun onSuccess()
    }

    interface Presenter {

        fun getWeatherInfo()

        fun storeTestBD(test: Test)
    }
}