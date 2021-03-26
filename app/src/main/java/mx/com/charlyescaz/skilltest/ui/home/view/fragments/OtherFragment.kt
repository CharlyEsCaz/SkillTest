package mx.com.charlyescaz.skilltest.ui.home.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import mx.com.charlyescaz.database.DBSkillTest
import mx.com.charlyescaz.skilltest.databinding.FragmentListBinding
import mx.com.charlyescaz.skilltest.models.Test
import mx.com.charlyescaz.skilltest.ui.details.view.OtherDetailsActivity
import mx.com.charlyescaz.skilltest.ui.home.data.HomeRepository
import mx.com.charlyescaz.skilltest.ui.home.data.OtherRepository
import mx.com.charlyescaz.skilltest.ui.home.presenter.HomePresenter
import mx.com.charlyescaz.skilltest.ui.home.presenter.OtherPresenter
import mx.com.charlyescaz.skilltest.ui.home.view.adapter.OtherAdapter
import mx.com.charlyescaz.skilltest.ui.home.view.interfaces.OtherView
import mx.com.charlyescaz.web.api.APISkilltest

class OtherFragment: Fragment(), OtherView {

    private lateinit var vBind: FragmentListBinding

    private val presenter: OtherPresenter by lazy {
        OtherPresenter(context!!,this, OtherRepository(DBSkillTest.db.skillTestDao()) )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBind = FragmentListBinding.inflate(inflater, container, false)
        presenter.getTestList()
        return vBind.root
    }


    private fun setupRecyclerView() {
        vBind.rvData.layoutManager = LinearLayoutManager(context)
        vBind.rvData.itemAnimator = DefaultItemAnimator()
    }

    private fun setupAdapter(tests: List<Test>) {

        val adapter = OtherAdapter(tests) { test ->
            val intent = Intent(context, OtherDetailsActivity::class.java)
            intent.putExtra(OtherDetailsActivity.TEST_ID, test.idLocalDB)
            startActivity(intent)
        }

        vBind.rvData.adapter = adapter
    }

    override fun showProgress() {
        vBind.pbLoading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        vBind.pbLoading.visibility = View.GONE
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onEmptyList() {
        vBind.lblEmpty.visibility = View.VISIBLE
    }

    override fun list(tests: List<Test>) {
        vBind.lblEmpty.visibility = View.GONE
        setupRecyclerView()
        setupAdapter(tests)
    }

}