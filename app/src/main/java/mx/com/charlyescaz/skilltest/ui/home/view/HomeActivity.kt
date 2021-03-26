package mx.com.charlyescaz.skilltest.ui.home.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mx.com.charlyescaz.database.DBSkillTest
import mx.com.charlyescaz.skilltest.R
import mx.com.charlyescaz.skilltest.databinding.ActivityHomeBinding
import mx.com.charlyescaz.skilltest.ui.home.data.HomeRepository
import mx.com.charlyescaz.skilltest.ui.home.presenter.HomePresenter
import mx.com.charlyescaz.skilltest.ui.home.view.interfaces.HomeView
import mx.com.charlyescaz.web.api.APISkilltest

class HomeActivity: AppCompatActivity(), HomeView {

//    private val vBind: ActivityHomeBinding by lazy {
//        DataBindingUtil.setContentView(this, R.layout.activity_home)
//    }

    private lateinit var vBind: ActivityHomeBinding

    private val presenter: HomePresenter by lazy {
        HomePresenter(this,this, HomeRepository(APISkilltest, DBSkillTest.db.skillTestDao()) )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBind = DataBindingUtil.setContentView(this, R.layout.activity_home)
        presenter.getWeatherInfo()
    }

    override fun showProgress() {
        Toast.makeText(this, "Cargando", Toast.LENGTH_SHORT).show()
    }

    override fun hideProgress() {
        Toast.makeText(this, "Finalizado", Toast.LENGTH_SHORT).show()
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        Toast.makeText(this, "Listo", Toast.LENGTH_SHORT).show()
    }
}