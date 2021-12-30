package com.example.program.util

import android.util.Log
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter

class XaxisDateFormatter : IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        Log.i("axisTest", "formatter, x축, original : $value, toInt : ${value.toInt()}")
        val temp = value.toInt().toString()

        return if (temp.length == 3) { // 앞 0이 짤렸을 경우

            val month = "0" + temp.substring(0, 1)
            val day = temp.subSequence(1, 3)
            "$month/$day"

        } else {

            val month = temp.substring(0, 2)
            val day = temp.subSequence(2, 4)
            "$month/$day"

        }
    }
}