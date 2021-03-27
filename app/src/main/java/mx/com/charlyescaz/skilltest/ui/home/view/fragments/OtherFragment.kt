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
import mx.com.charlyescaz.skilltest.R
import mx.com.charlyescaz.skilltest.databinding.FragmentListBinding
import mx.com.charlyescaz.skilltest.models.Test
import mx.com.charlyescaz.skilltest.ui.details.view.OtherDetailsActivity
import mx.com.charlyescaz.skilltest.ui.home.contract.OtherContract
import mx.com.charlyescaz.skilltest.ui.home.data.HomeRepository
import mx.com.charlyescaz.skilltest.ui.home.data.OtherRepository
import mx.com.charlyescaz.skilltest.ui.home.presenter.OtherPresenter
import mx.com.charlyescaz.skilltest.ui.home.view.adapter.OtherAdapter
import javax.inject.Inject

class OtherFragment(private val repository: OtherRepository): Fragment(), OtherContract.View {

    private lateinit var vBind: FragmentListBinding


    private val presenter: OtherPresenter by lazy {
        OtherPresenter(this, repository)
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

    override fun showErrorMessage() {
        Toast.makeText(context, getString(R.string.error_local_save), Toast.LENGTH_LONG).show()
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