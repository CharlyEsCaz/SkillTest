package mx.com.charlyescaz.skilltest.ui.details.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mx.com.charlyescaz.skilltest.R
import mx.com.charlyescaz.skilltest.databinding.ActivityDetailsBinding

class OtherDetailsActivity: AppCompatActivity() {

    companion object {
        const val TEST_ID = "TEST_ID"
    }

    private lateinit var vBind: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBind = DataBindingUtil.setContentView(this, R.layout.activity_details)
    }
}