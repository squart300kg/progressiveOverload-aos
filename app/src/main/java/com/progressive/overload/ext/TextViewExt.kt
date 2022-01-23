package com.progressive.overload.ext

import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.firebase.Timestamp
import java.text.DecimalFormat
import java.text.SimpleDateFormat

@BindingAdapter("overload:setVolume")
fun TextView.setVolume(volume: Int) {
    this.text = DecimalFormat("###,###").format(volume)+"KG"
}
@BindingAdapter("overload:setSubTitleWeek", "overload:setSubTitleDay")
fun TextView.setSubTitle(week: String, day: String) {
    this.text = week.substring(0, 1) + "WEEK " +day.substring(0, 1) + "DAY"
}


@SuppressLint("LongLogTag")
@BindingAdapter("theWarsOfStar:setReviewCount")
fun TextView.setReviewCount(text: Long) {
    val TAG = "BindingAdapter_setReviewCountLog"
    Log.i(TAG, text.toString())
    this.text = "후기 ${text}개"
}

@SuppressLint("LongLogTag")
@BindingAdapter("theWarsOfStar:setDate")
fun TextView.setDate(timestamp: Timestamp) {
    val TAG = "BindingAdapter_setDateLog"
    val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd")
    val result = simpleDateFormat.format(timestamp.toDate())
    Log.i(TAG, timestamp.toString())
    Log.i(TAG, timestamp.toDate().toString())
    Log.i(TAG, result)
    this.text = result
}

@SuppressLint("LongLogTag")
@BindingAdapter("theWarsOfStar:setCurrentDate")
fun TextView.setCurrentDate(timestamp: Long) {
    val TAG = "BindingAdapter_setCurrentDateLog"

    val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd\nHH:mm")
    val date = simpleDateFormat.format(timestamp)
    this.text = date.toString()

}


@BindingAdapter("theWarsOfStar:setPrice")
fun TextView.setPrice(inputPrice: Long) {
    val formatter = DecimalFormat("###,###")
    val price = formatter.format(inputPrice)
    this.text = "$price / 1판"
}