package com.spitfire.weather_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.spitfire.weather_app.databinding.FragmentOneDayDetailBinding
import com.spitfire.weather_app.model.CityDailyResponse

class CityDailyDetailFragment: Fragment() {

    private lateinit var dataBinding: FragmentOneDayDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_one_day_detail, container, false)
        var cityDailyResponse = arguments?.getParcelable<CityDailyResponse.Forecast>("cityWeatherDetail")
        dataBinding.detail = cityDailyResponse
        return dataBinding.root
    }
}