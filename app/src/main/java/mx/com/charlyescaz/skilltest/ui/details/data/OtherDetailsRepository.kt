package mx.com.charlyescaz.skilltest.ui.details.data

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mx.com.charlyescaz.database.dao.SkillTestDao
import mx.com.charlyescaz.database.models.TestBD
import mx.com.charlyescaz.database.models.WeatherBD

class OtherDetailsRepository(private val dao: SkillTestDao) {

    fun getTest(idLocal: Long, cb: (success: Boolean, data: TestBD?) -> Unit): Disposable {
        return dao.findById(idLocal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { cb(true, it) },
                { cb(false, null) })
    }

    fun getWeathersOfTest(testId: Int, cb: (success: Boolean, data: List<WeatherBD>?) -> Unit): Disposable {
        return dao.findWeathersById(testId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { cb(true, it) },
                { cb(false, null) })
    }
}