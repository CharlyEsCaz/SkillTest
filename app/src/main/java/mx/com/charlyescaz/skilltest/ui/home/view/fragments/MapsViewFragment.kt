package mx.com.charlyescaz.skilltest.ui.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import mx.com.charlyescaz.database.DBSkillTest
import mx.com.charlyescaz.skilltest.R
import mx.com.charlyescaz.skilltest.databinding.FragmentMapBinding
import mx.com.charlyescaz.skilltest.models.Test
import mx.com.charlyescaz.skilltest.ui.home.contract.MapContract
import mx.com.charlyescaz.skilltest.ui.home.data.MapsViewRepository
import mx.com.charlyescaz.skilltest.ui.home.data.OtherRepository
import mx.com.charlyescaz.skilltest.ui.home.presenter.MapsViewPresenter
import mx.com.charlyescaz.skilltest.utils.map.GMapHelper
import mx.com.charlyescaz.skilltest.utils.map.MapInterface
import javax.inject.Inject

class MapsViewFragment(private val repository: MapsViewRepository): Fragment(), MapContract.View, MapInterface {

    private lateinit var vBind: FragmentMapBinding

    private val presenter: MapsViewPresenter by lazy {
        MapsViewPresenter(this, repository)
    }

    private val gMapHelper: GMapHelper by lazy {
        GMapHelper(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBind = FragmentMapBinding.inflate(inflater, container, false)
        setupMap()
        return vBind.root
    }

    private fun setupMap() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.fr_map) as SupportMapFragment?
        mapFragment!!.getMapAsync(gMapHelper)
    }

    override fun showProgress() {
        vBind.pbLoading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        vBind.pbLoading.visibility = View.GONE
    }

    override fun showErrorMessage() {
        Toast.makeText(context, getString(R.string.error_local_get), Toast.LENGTH_LONG).show()
    }

    override fun showMarker(test: Test) {
        val location = LatLng(test.coord?.lat!!, test.coord.lon)
        gMapHelper.centerPosition(location)

        gMapHelper.setMarker(
            location, test.name,
        )
    }

    override fun onMapCompletelyConfigured(gMap: GoogleMap?) {
        presenter.getLastTest()
    }
}