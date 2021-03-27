package mx.com.charlyescaz.skilltest.ui.details.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mx.com.charlyescaz.database.DBSkillTest
import mx.com.charlyescaz.skilltest.R
import mx.com.charlyescaz.skilltest.SkillTestApp
import mx.com.charlyescaz.skilltest.databinding.ActivityDetailsBinding
import mx.com.charlyescaz.skilltest.databinding.ItemWeatherBinding
import mx.com.charlyescaz.skilltest.models.Test
import mx.com.charlyescaz.skilltest.models.Weather
import mx.com.charlyescaz.skilltest.ui.details.contract.OtherDetailsContract
import mx.com.charlyescaz.skilltest.ui.details.data.OtherDetailsRepository
import mx.com.charlyescaz.skilltest.ui.details.presenter.OtherDetailsPresenter
import mx.com.charlyescaz.skilltest.ui.home.data.HomeRepository
import javax.inject.Inject

class OtherDetailsActivity: AppCompatActivity(), OtherDetailsContract.View {

    companion object {
        const val TEST_ID = "TEST_ID"
    }

    private val vBind: ActivityDetailsBinding by lazy{
        DataBindingUtil.setContentView(this, R.layout.activity_details)
    }

    @Inject
    lateinit var repository: OtherDetailsRepository

    private val presenter: OtherDetailsPresenter by lazy {
        OtherDetailsPresenter(this, repository )
    }

    private var testId: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as SkillTestApp).getHomeComponent().inject(this)

        setupValues()
        presenter.getTestInfo(testId)
    }

    private fun setupValues(){
        testId = intent.getLongExtra(TEST_ID, 0L)
    }

    override fun showProgress() {
        vBind.pbLoading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        vBind.pbLoading.visibility = View.GONE
    }

    override fun showErrorMessage() {
        Toast.makeText(this, getString(R.string.error_local_get), Toast.LENGTH_LONG).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onSuccess(test: Test, weathers: List<Weather>) {
        vBind.apply {
            this.test = test
            tvCoordinates.text = "${test.coord?.lat} , ${test.coord?.lon}"
            tvVisibility.text = "${test.visibility}"
            tvTimezone.text = "${test.timezone}"
            tvDt.text = "${test.dt}"
            tvTemp.text = "${getString(R.string.temp)}: ${test.main?.temp}"
            tvLike.text = "${getString(R.string.feels_like)}: ${test.main?.feelsLike}"
            tvTempMin.text = "${getString(R.string.temp_min)}: ${test.main?.tempMin}"
            tvTempMax.text = "${getString(R.string.temp_max)}: ${test.main?.tempMax}"
            tvPressure.text = "${getString(R.string.pressure)}: ${test.main?.pressure}"
            tvHumidity.text = "${getString(R.string.humidity)}: ${test.main?.humidity}"
            tvSpeed.text = "${getString(R.string.speed)}: ${test.wind?.speed}"
            tvDeg.text = "${getString(R.string.deg)}: ${test.wind?.deg}"
            tvAll.text = "${getString(R.string.all)}: ${test.clouds?.all}"
            tvType.text = "${getString(R.string.type)}: ${test.sys?.type}"
            tvSunrise.text = "${getString(R.string.sunrise)}: ${test.sys?.sunrise}"
            tvSunset.text = "${getString(R.string.sunset)}: ${test.sys?.sunset}"

            llWeathers.removeAllViews()
            weathers.forEach { weather ->
                val view = LayoutInflater.from(this@OtherDetailsActivity).inflate(R.layout.item_weather, llWeathers, false)
                val weatherBinding = ItemWeatherBinding.bind(view)
                weatherBinding.weather = weather
                llWeathers.addView(view)
            }
        }
    }
}