package mx.com.charlyescaz.skilltest.ui.home.presenter

import android.content.Context
import mx.com.charlyescaz.skilltest.R
import mx.com.charlyescaz.skilltest.models.Test
import mx.com.charlyescaz.skilltest.ui.home.data.OtherRepository
import mx.com.charlyescaz.skilltest.ui.home.view.interfaces.OtherView
import mx.com.charlyescaz.skilltest.utils.ModelConverter.toListModel

class OtherPresenter(
    private val context: Context,
    private val view: OtherView,
    private val repository: OtherRepository
) {

    fun getTestList() {
        view.showProgress()

        repository.getTests() { success, data ->

            view.hideProgress()

            if (!success || data == null) {
                view.showErrorMessage(context.getString(R.string.error_local_save))
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