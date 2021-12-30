package com.example.program.ext

import android.graphics.Color
import android.util.Log
import androidx.databinding.BindingAdapter
import com.example.program.model.model.RecordModel
import com.example.program.util.DateUtil
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@BindingAdapter("overload:setAxisData")
fun com.github.mikephil.charting.charts.LineChart.setAxisData(datas: List<RecordModel>?) {

    datas?.let {
        val entries = arrayListOf<Entry>()

        for (index in datas.indices) {

            // x축 날짜 구하기
            val recordTime = datas.reversed()[index].recordTime
            val xAxisDate = DateUtil.getMonthDayFromDate(recordTime)

            // y축 총볼륨 구하기
            val yAxisTotalVolume = datas.reversed()[index].totalVolume

            entries.add(
                Entry(xAxisDate.toFloat(), yAxisTotalVolume.toFloat())
            )
        }

        LineDataSet(entries, "총 볼륨").apply {
            lineWidth = 2f
            circleRadius = 6f
            setCircleColor(Color.parseColor("#FFA1B4DC"))
            setCircleColorHole(Color.BLUE)
            color = Color.parseColor("#FFA1B4DC")
            setDrawCircleHole(true)
            setDrawCircles(true)
            setDrawHorizontalHighlightIndicator(false)
            setDrawHighlightIndicators(false)
            setDrawValues(false)

            data = LineData(this)
        }
    }

}