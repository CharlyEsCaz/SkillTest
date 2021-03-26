package mx.com.charlyescaz.skilltest.ui.home.data

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mx.com.charlyescaz.database.dao.SkillTestDao
import mx.com.charlyescaz.database.models.TestBD

class MapsViewRepository(private val dao: SkillTestDao) {


    fun count(cb: (success: Boolean, data: Int?) -> Unit): Disposable {
        return dao.count()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { cb(true, it) },
                { cb(false, null) }
            )
    }

    fun getTests(cb: (success: Boolean, data: List<TestBD>?) -> Unit): Disposable? {
        return dao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { cb(true, it) },
                { cb(false, null) })
    }
}