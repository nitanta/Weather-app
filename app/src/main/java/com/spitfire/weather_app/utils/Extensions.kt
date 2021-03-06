package com.spitfire.weather_app.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*
import androidx.databinding.BindingAdapter
import com.spitfire.weather_app.R

fun dateConverter(): String {
    var date = Calendar.getInstance().time
    var converter = SimpleDateFormat("EEE, d MMM yyyy", Locale("en"))
    var convertedDate = converter.format(date)
    return convertedDate
}

fun timeConverter(time: Long): String {
    var converter = SimpleDateFormat("hh:mm a")
    var convertedTime = converter.format(Date(time*1000))

    return convertedTime
}

fun dayConverter(time: Long) : String{
    var converter = SimpleDateFormat("EEE, d MMM yyyy hh:mm a")
    var convertedDay = converter.format(Date(time*1000))

    return convertedDay
}

@BindingAdapter("android:dayConverter")
fun convertToDay(view: TextView, value:Long){
    var converter = SimpleDateFormat("EEE, d MMM yyyy hh:mm a")
    var convertedDay = converter.format(Date(value*1000))
    view.text = convertedDay
}

@BindingAdapter("android:converterInt")
fun convertToInt(view: TextView, value:Double){
    var valueInt = value.toInt()
    var valueStrin = valueInt.toString()
    view.text = valueStrin
}

@BindingAdapter("layoutBackgroundImage")
fun setLayoutBackgroundImage(constraintLayout: ConstraintLayout, url: String?){
    when(url){
        "01d","02d","03d","04d","09d","10d","11d","13d","50d" -> {
            constraintLayout.setBackgroundResource(constraintLayout.resources.getIdentifier("ic_background_daylight","drawable", constraintLayout.context.packageName))
        }
        "01n","02n","03n","04n","09n","10n","11n","13n","50n" -> {
            constraintLayout.setBackgroundResource(constraintLayout.resources.getIdentifier("ic_background_night","drawable", constraintLayout.context.packageName))
        }
    }

}

@BindingAdapter("imageResource")
fun setImageResource(imageview: ImageView, url:String){
    when(url){
        "01d" -> imageview.setImageDrawable(imageview, R.drawable.ic_01d)
        "01n" -> imageview.setImageDrawable(imageview, R.drawable.ic_01n)
        "02d" -> imageview.setImageDrawable(imageview, R.drawable.ic_02d)
        "02n" -> imageview.setImageDrawable(imageview, R.drawable.ic_02n)
        "03d" -> imageview.setImageDrawable(imageview, R.drawable.ic_03d)
        "03n" -> imageview.setImageDrawable(imageview, R.drawable.ic_03n)
        "04d" -> imageview.setImageDrawable(imageview, R.drawable.ic_04d)
        "04n" -> imageview.setImageDrawable(imageview, R.drawable.ic_04n)
        "09d" -> imageview.setImageDrawable(imageview, R.drawable.ic_09d)
        "09n" -> imageview.setImageDrawable(imageview, R.drawable.ic_09n)
        "10d" -> imageview.setImageDrawable(imageview, R.drawable.ic_10d)
        "10n" -> imageview.setImageDrawable(imageview, R.drawable.ic_10n)
        "11d" -> imageview.setImageDrawable(imageview, R.drawable.ic_11d)
        "11n" -> imageview.setImageDrawable(imageview, R.drawable.ic_11n)
        "13d" -> imageview.setImageDrawable(imageview, R.drawable.ic_13d)
        "13n" -> imageview.setImageDrawable(imageview, R.drawable.ic_13n)
        "50d" -> imageview.setImageDrawable(imageview, R.drawable.ic_50d)
        "50n" -> imageview.setImageDrawable(imageview, R.drawable.ic_50n)
    }

}

private fun ImageView.setImageDrawable(view:ImageView,image: Int) {
    Glide.with(context).load(image).into(view)
}
