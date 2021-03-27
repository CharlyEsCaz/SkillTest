package mx.com.charlyescaz.skilltest.ui.home.presenter

import android.content.Context
import mx.com.charlyescaz.skilltest.R
import mx.com.charlyescaz.skilltest.models.Test
import mx.com.charlyescaz.skilltest.ui.home.contract.OtherContract
import mx.com.charlyescaz.skilltest.ui.home.data.OtherRepository
import mx.com.charlyescaz.skilltest.utils.ModelConverter.toListModel

class OtherPresenter(
    private val view: OtherContract.View,
    private val repository: OtherRepository
): OtherContract.Presenter {

    override fun getTestList() {
        view.showProgress()

        repository.getTests() { success, data ->

            view.hideProgress()

            if (!success || data == null) {
                view.showErrorMessage()
                return@getTests
            }

            if (data.isEmpty()) {
                view.onEmptyList()
            } else {
                view.list(data.toListModel(Test::class.java))
            }
        }
    }
}