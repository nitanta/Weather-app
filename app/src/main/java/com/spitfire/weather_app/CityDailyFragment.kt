package com.spitfire.weather_app

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.spitfire.weather_app.adapter.CityDailyAdapter
import com.spitfire.weather_app.databinding.FragmentCityDailyBinding
import com.spitfire.weather_app.utils.Constants
import com.spitfire.weather_app.viewmodel.CityDailyViewModel
import im.delight.android.location.SimpleLocation
import kotlinx.android.synthetic.main.fragment_city_daily.*
import kotlinx.android.synthetic.main.fragment_five_days.*
import kotlinx.android.synthetic.main.fragment_five_days.recyclerView


class CityDailyFragment: Fragment() {

    private val REQUEST_CODE = 1

    var location: SimpleLocation? = null
    val latitude: String? = null
    val longitude: String? = null

    private lateinit var viewModel: CityDailyViewModel
    private lateinit var dataBinding: FragmentCityDailyBinding

    private var cityDailyAdapter = CityDailyAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_city_daily, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = cityDailyAdapter

        viewModel = ViewModelProviders.of(this).get(CityDailyViewModel::class.java)

        location = SimpleLocation(context)
        if (!location!!.hasLocationEnabled()) {
            SimpleLocation.openSettings(context)
        } else {
            if (ContextCompat.checkSelfPermission(
                    activity!!,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_CODE
                )
            } else {
                location = SimpleLocation(context)
                latitude = String.format("%.6f", location?.latitude)
                longitude = String.format("%.6f", location?.longitude)
                Log.e("LAT1", "" + latitude)
                Log.e("LONG1", "" + longitude)

            }
        }

        viewModel.getCityDailyWeatherFromGps(latitude!!,longitude!!,Constants.CNT,Constants.METRIC)

        viewModel.cityDailyData.observe(viewLifecycleOwner, { cityDailyWeatherGps ->
            cityDailyWeatherGps.let {
                recyclerView.visibility = View.VISIBLE
                cityDailyAdapter.updateCountryList(cityDailyWeatherGps)
            }
        })

        viewModel.cityDailyLoading.observe(viewLifecycleOwner, { loading ->
            loading?.let {
                if (it){
                    cityDailyLoading.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }else{
                    cityDailyLoading.visibility = View.GONE
                }
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                location = SimpleLocation(context)
                latitude = String.format("%.6f", location?.latitude)
                longitude = String.format("%.6f", location?.longitude)
                Log.e("LAT", "" + latitude)
                Log.e("LONG", "" + longitude)

                viewModel.getCityDailyWeatherFromGps(latitude!!,longitude!!,Constants.CNT,Constants.METRIC)

            } else {
                Toast.makeText(context, "Please grant location access :P", Toast.LENGTH_LONG)
                    .show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}