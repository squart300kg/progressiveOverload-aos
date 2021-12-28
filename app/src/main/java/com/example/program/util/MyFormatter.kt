package com.example.program.util

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter

class MyFormatter : IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        when (value % 7) {
            0f -> { return "mon"}
            1f -> { return "tue"}
            2f -> { return "wed"}
            3f -> { return "thu"}
            4f -> { return "fri"}
            5f -> { return "sat"}
            6f -> { return "sun"}
            else -> { return "else"}
        }
    }

}