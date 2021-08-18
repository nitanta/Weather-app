package com.spitfire.weather_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.spitfire.weather_app.R
import com.spitfire.weather_app.databinding.ForcastWeatherHourlyItemBinding
import com.spitfire.weather_app.model.ForecastResponse
import com.spitfire.weather_app.utils.dayConverter


class HourlyAdapter(val hourlyList: ArrayList<ForecastResponse.Forecast>) :
    RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder>() {

    class HourlyViewHolder(var view: ForcastWeatherHourlyItemBinding) :
        RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ForcastWeatherHourlyItemBinding>(
            inflater,
            R.layout.forcast_weather_hourly_item,
            parent,
            false
        )
        return HourlyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hourlyList.size
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.view.forecast = hourlyList[position]

        holder.view.tvForecastTime.text = dayConverter((hourlyList[position].dt).toLong())
        holder.view.tvForecastTemp.text = hourlyList[position].main!!.temp.toInt().toString()
        holder.view.tvForecastFeelsTemp.text = hourlyList[position].main!!.feelsLike.toInt().toString()


    }

    fun updateHourlyList(newHourlyList: List<ForecastResponse.Forecast>){
        hourlyList.clear()
        hourlyList.addAll(newHourlyList)
        notifyDataSetChanged()
    }


}