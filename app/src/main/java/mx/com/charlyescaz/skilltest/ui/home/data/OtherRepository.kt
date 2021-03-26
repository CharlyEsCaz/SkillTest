package mx.com.charlyescaz.skilltest.ui.home.data

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mx.com.charlyescaz.database.dao.SkillTestDao
import mx.com.charlyescaz.database.models.TestBD

class OtherRepository(private val dao: SkillTestDao) {

    fun getTests(cb: (success: Boolean, data: List<TestBD>?) -> Unit): Disposable? {
        return dao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { cb(true, it) },
                { cb(false, null) })
    }
}