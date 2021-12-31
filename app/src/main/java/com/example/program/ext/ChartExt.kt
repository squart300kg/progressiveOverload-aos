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

        if (datas.isNotEmpty()) {

            // 그래프 데이터 채우기
            val entries = arrayListOf<Entry>()
            datas.reversed().forEach { recordModel ->

                // x축 날짜 구하기
                val recordTime = recordModel.recordTime
                val xAxisDate = DateUtil.getMonthDayFromDate(recordTime)

                // y축 총볼륨 구하기
                val yAxisTotalVolume = recordModel.totalVolume

                entries.add(
                    Entry(xAxisDate.toFloat(), yAxisTotalVolume.toFloat())
                )
            }

            // 그래프에 데이터 바인딩
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

}