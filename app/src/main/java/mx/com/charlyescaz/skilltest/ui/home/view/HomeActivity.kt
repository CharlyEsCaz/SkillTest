package mx.com.charlyescaz.skilltest.ui.home.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import mx.com.charlyescaz.database.DBSkillTest
import mx.com.charlyescaz.skilltest.R
import mx.com.charlyescaz.skilltest.databinding.ActivityHomeBinding
import mx.com.charlyescaz.skilltest.ui.home.contract.HomeContract
import mx.com.charlyescaz.skilltest.ui.home.data.HomeRepository
import mx.com.charlyescaz.skilltest.ui.home.presenter.HomePresenter
import mx.com.charlyescaz.skilltest.ui.home.view.fragments.MapsViewFragment
import mx.com.charlyescaz.skilltest.ui.home.view.fragments.OtherFragment
import mx.com.charlyescaz.skilltest.utils.Codes
import mx.com.charlyescaz.web.api.APISkilltest

class HomeActivity: AppCompatActivity(), HomeContract.View {

    private lateinit var vBind: ActivityHomeBinding
    private var currentFragmentTag = ""

    private val presenter: HomePresenter by lazy {
        HomePresenter(this, this, HomeRepository(APISkilltest, DBSkillTest.db.skillTestDao()))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBind = DataBindingUtil.setContentView(this, R.layout.activity_home)

        setupMainFragment()
        setupBottomNavigation()
    }

    private fun setupMainFragment() {

        if (supportFragmentManager.findFragmentByTag(Codes.FRAGMENT_LIST) != null) return

        currentFragmentTag = Codes.FRAGMENT_LIST

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fl_content, OtherFragment(), Codes.FRAGMENT_LIST)
            .commit()
    }

    private fun replaceFragment(tag: String, fragment: Fragment) {
        if (supportFragmentManager.findFragmentByTag(tag) != null) return

        currentFragmentTag = tag

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_content, fragment, tag)
            .commit()
    }

    private fun refreshFragment(){
        val fragment = supportFragmentManager.findFragmentByTag(currentFragmentTag)!!

        supportFragmentManager
            .beginTransaction()
            .detach(fragment)
            .attach(fragment)
            .commit()
    }

    private fun setupBottomNavigation() {
        vBind.menuBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.i_download -> {
                    presenter.getWeatherInfo()
                    true
                }
                R.id.i_map -> {
                    replaceFragment(Codes.FRAGMENT_MAP, MapsViewFragment())
                    true
                }
                R.id.i_list -> {
                    replaceFragment(Codes.FRAGMENT_LIST, OtherFragment())
                    true
                }
                else -> false
            }
        }
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

    override fun onSuccess() {
        Toast.makeText(this, getString(R.string.success_local_get), Toast.LENGTH_LONG).show()
        refreshFragment()
    }
}