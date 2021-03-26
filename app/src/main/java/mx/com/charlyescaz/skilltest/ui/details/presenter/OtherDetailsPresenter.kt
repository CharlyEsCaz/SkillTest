package mx.com.charlyescaz.skilltest.ui.details.presenter

import android.content.Context
import mx.com.charlyescaz.skilltest.R
import mx.com.charlyescaz.skilltest.models.Test
import mx.com.charlyescaz.skilltest.models.Weather
import mx.com.charlyescaz.skilltest.ui.details.contract.OtherDetailsContract
import mx.com.charlyescaz.skilltest.ui.details.data.OtherDetailsRepository
import mx.com.charlyescaz.skilltest.utils.ModelConverter.toListModel
import mx.com.charlyescaz.skilltest.utils.ModelConverter.toTest

class OtherDetailsPresenter(
    private val context: Context,
    private val view: OtherDetailsContract.View,
    private val repository: OtherDetailsRepository
): OtherDetailsContract.Presenter {

    override fun getTestInfo(id: Long) {
        view.showProgress()

        repository.getTest(id) { success, data ->

            view.hideProgress()

            if (!success || data == null) {
                view.showErrorMessage(context.getString(R.string.error_local_get))
                return@getTest
            }

            getWeathers(data.toTest())
        }
    }

    private fun getWeathers(test: Test){
        repository.getWeathersOfTest(test.idLocalDB!!){ success, data ->

            if (!success || data == null) {
                view.showErrorMessage(context.getString(R.string.error_local_get))
                return@getWeathersOfTest
            }

            val weathers = data.toListModel(Weather::class.java)

            view.onSuccess(test, weathers)
        }
    }
}