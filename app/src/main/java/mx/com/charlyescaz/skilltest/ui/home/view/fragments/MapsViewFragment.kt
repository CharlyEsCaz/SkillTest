package mx.com.charlyescaz.skilltest.ui.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mx.com.charlyescaz.skilltest.databinding.FragmentMapBinding

class MapsViewFragment: Fragment() {

    private lateinit var vBind: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBind = FragmentMapBinding.inflate(inflater, container, false)
        return vBind.root
    }
}