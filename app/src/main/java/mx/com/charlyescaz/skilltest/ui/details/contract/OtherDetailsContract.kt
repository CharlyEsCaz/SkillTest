package mx.com.charlyescaz.skilltest.ui.details.contract

import mx.com.charlyescaz.skilltest.models.Test
import mx.com.charlyescaz.skilltest.models.Weather

interface OtherDetailsContract {

    interface View {
        fun showProgress()

        fun hideProgress()

        fun showErrorMessage(message: String)

        fun onSuccess(test: Test, weathers: List<Weather>)
    }

    interface Presenter {

        fun getTestInfo(id: Long)
    }
}