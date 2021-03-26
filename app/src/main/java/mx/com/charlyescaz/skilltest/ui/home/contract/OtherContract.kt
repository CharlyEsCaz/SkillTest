package mx.com.charlyescaz.skilltest.ui.home.contract

import mx.com.charlyescaz.skilltest.models.Test

interface OtherContract {

    interface View {
        fun showProgress()

        fun hideProgress()

        fun showErrorMessage(message: String)

        fun onEmptyList()

        fun list(tests: List<Test>)
    }

    interface Presenter {

        fun getTestList()
    }
}