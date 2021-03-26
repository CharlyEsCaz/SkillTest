package mx.com.charlyescaz.skilltest.ui.home.data

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mx.com.charlyescaz.database.dao.SkillTestDao
import mx.com.charlyescaz.database.models.TestBD
import mx.com.charlyescaz.database.models.WeatherBD
import mx.com.charlyescaz.web.api.APISkilltest

class HomeRepository(private val api: APISkilltest, private val dao: SkillTestDao) {

    fun getWeatherInformation(cb: (success: Boolean, data: String?) -> Unit) {
        api.getWeather("London,uk",
                "0",
                "0",
                "test",
                "2172797",
                null,
                    "'metric' or 'imperial'",
                "xml, html"){ success, data ->

            if (!success || data == null){
                cb(false, null)
                return@getWeather
            }

            cb(true, data)
        }
    }

    fun storeTest(test: TestBD, cb: (success: Boolean, data: Long?) -> Unit): Disposable {
        return dao.upsert(test)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { cb(true, it) },
                { cb(false, null) }
            )
    }

    fun storeWeather(weather: WeatherBD, cb: (success: Boolean) -> Unit): Disposable {
        return dao.upsertWeather(weather)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { cb(true) },
                { cb(false) }
            )
    }
}