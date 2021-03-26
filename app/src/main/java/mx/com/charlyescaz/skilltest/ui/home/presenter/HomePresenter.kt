package mx.com.charlyescaz.skilltest.ui.home.presenter

import android.content.Context
import com.google.gson.Gson
import mx.com.charlyescaz.database.models.TestBD
import mx.com.charlyescaz.skilltest.R
import mx.com.charlyescaz.skilltest.models.Test
import mx.com.charlyescaz.skilltest.ui.home.data.HomeRepository
import mx.com.charlyescaz.skilltest.ui.home.view.interfaces.HomeView
import mx.com.charlyescaz.skilltest.utils.ModelConverter
import mx.com.charlyescaz.skilltest.utils.ModelConverter.toTestBB
import mx.com.charlyescaz.skilltest.utils.ModelConverter.toWeatherBD
import org.json.JSONObject

class HomePresenter(
        private val context: Context,
        private val view: HomeView,
        private val repository: HomeRepository) {

    fun getWeatherInfo(){
        view.showProgress()
        repository.getWeatherInformation { success, data ->
            view.hideProgress()
            if (!success || data == null) {
                view.showErrorMessage(context.getString(R.string.error_api_get))
                return@getWeatherInformation
            }

            val test= ModelConverter.convertStringToTestModel(data)
            storeTestBD(test)

            view.onSuccess()
        }
    }

    fun storeTestBD(test: Test) {
        val testDb = test.toTestBB()

        repository.storeTest(testDb) { success, data ->
            if (!success || data == null) {
                view.showErrorMessage(context.getString(R.string.error_local_save))
                return@storeTest
            }

            test.weather?.forEach { weather ->
                val weatherDb = weather.toWeatherBD()
                weatherDb.fkIdTest = data

                repository.storeWeather(weatherDb){ success ->
                    if (!success) {
                        view.showErrorMessage(context.getString(R.string.error_local_save))
                        return@storeWeather
                    }
                }
            }
        }
    }


}