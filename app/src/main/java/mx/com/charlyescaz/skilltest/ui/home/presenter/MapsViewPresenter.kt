package mx.com.charlyescaz.skilltest.ui.home.presenter

import android.content.Context
import mx.com.charlyescaz.skilltest.R
import mx.com.charlyescaz.skilltest.ui.home.contract.MapContract
import mx.com.charlyescaz.skilltest.ui.home.data.MapsViewRepository
import mx.com.charlyescaz.skilltest.utils.ModelConverter.toTest

class MapsViewPresenter(
    private val context: Context,
    private val view: MapContract.View,
    private val repository: MapsViewRepository
): MapContract.Presenter {

    override fun getLastTest() {
        view.showProgress()

        repository.getTests { success, data ->
            view.hideProgress()

            if (!success || data == null) {
                view.showErrorMessage(context.getString(R.string.error_local_get))
                return@getTests
            }

            if(data.isEmpty()){
                view.showErrorMessage(context.getString(R.string.error_local_count))
                return@getTests
            }

            val test = data[data.size - 1].toTest()
            view.showMarker(test)
        }
    }
}