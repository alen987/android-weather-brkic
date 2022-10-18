package com.example.weatherbrkic.ui.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.weatherbrkic.databinding.FragmentMainBinding
import com.example.weatherbrkic.data.network.AccuWeatherApi
import com.example.weatherbrkic.data.model.Location
import com.example.weatherbrkic.ui.locations.adapters.LocationsAdapter
import com.example.weatherbrkic.utils.viewmodel.viewModel

class LocationsFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel {
        MainViewModel(LocationsRepository(AccuWeatherApi.retrofitService))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val data: ArrayList<Location> =
            if (viewModel.locations.value.isNullOrEmpty()) arrayListOf() else viewModel.locations.value as ArrayList<Location>
        binding.acLocations.setAdapter(LocationsAdapter(requireContext(), data))
        binding.acLocations.setOnItemClickListener { adapter, _, i, _ ->
            val item = adapter?.getItemAtPosition(i) as Location
            viewModel.getForecastForOneDay(item.Key)
        }

        return binding.root
    }

}