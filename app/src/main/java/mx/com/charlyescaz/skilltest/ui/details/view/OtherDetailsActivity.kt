package mx.com.charlyescaz.skilltest.ui.details.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mx.com.charlyescaz.database.DBSkillTest
import mx.com.charlyescaz.skilltest.R
import mx.com.charlyescaz.skilltest.databinding.ActivityDetailsBinding
import mx.com.charlyescaz.skilltest.models.Test
import mx.com.charlyescaz.skilltest.models.Weather
import mx.com.charlyescaz.skilltest.ui.details.data.OtherDetailsRepository
import mx.com.charlyescaz.skilltest.ui.details.presenter.OtherDetailsPresenter
import mx.com.charlyescaz.skilltest.ui.details.view.interfaces.OtherDetailsView
import mx.com.charlyescaz.skilltest.ui.home.data.OtherRepository
import mx.com.charlyescaz.skilltest.ui.home.presenter.OtherPresenter

class OtherDetailsActivity: AppCompatActivity(), OtherDetailsView {

    companion object {
        const val TEST_ID = "TEST_ID"
    }

    private lateinit var vBind: ActivityDetailsBinding

    private val presenter: OtherDetailsPresenter by lazy {
        OtherDetailsPresenter(this,this, OtherDetailsRepository(DBSkillTest.db.skillTestDao()) )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBind = DataBindingUtil.setContentView(this, R.layout.activity_details)
    }

    override fun showProgress() {
        vBind.pbLoading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        vBind.pbLoading.visibility = View.GONE
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onSuccess(test: Test, weathers: List<Weather>) {
        
    }
}