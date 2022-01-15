package com.progressive.overload.util

import android.util.Log
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.text.DecimalFormat

class YaxisVolumeFormatter : IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        Log.i("graph", "formatter, y축, original : $value")

        return DecimalFormat("###,###").format(value.toInt()) + "KG"
    }
}