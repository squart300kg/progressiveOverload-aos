package com.example.program.ui.record.sub

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityRecordDetailBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecordDetailActivity :
    BaseActivity<ActivityRecordDetailBinding>(R.layout.activity_record_detail) {

    private val recordDetailViewModel: RecordDetailViewModel by viewModel()

    private var programNo: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        programNo = intent.getLongExtra("programNo", 0L)

        binding {
            recordDetailVm = recordDetailViewModel

            chart.apply {
                val entries = arrayListOf(
                    Entry(1f, 1f),
                    Entry(2f, 2f),
                    Entry(3f, 0f),
                    Entry(4f, 4f),
                    Entry(5f, 3f),
                )
                val lineDateSet = LineDataSet(entries, "속성명1").apply {
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
                }

                val lineData = LineData(lineDateSet)
                data = lineData

                val xAxis: XAxis = xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.textColor = Color.BLACK
                xAxis.enableGridDashedLine(8f, 24f, 0f)

                val yLAxis: YAxis = axisLeft
                yLAxis.textColor = Color.BLACK

                val yRAxis: YAxis = axisRight
                yRAxis.setDrawLabels(false)
                yRAxis.setDrawAxisLine(false)
                yRAxis.setDrawGridLines(false)

                val description = Description()
                description.text = ""

                isDoubleTapToZoomEnabled = false;
                setDrawGridBackground(false);
                setDescription(description);
                animateY(2000, Easing.EasingOption.EaseInCubic);
                invalidate();

//                val marker = RecordMarkerView(this@RecordDetailActivity, R.layout.layout_graph_marker)
//                marker.chartView = chart
//                chart.marker = marker
            }

            rvRecord.apply {
                setHasFixedSize(true)
                adapter = RecordsAdapter(this@RecordDetailActivity)
                { recordTime ->
                    Intent(this@RecordDetailActivity, OneDayRecordActivity::class.java).apply {
                        putExtra("programNo", programNo)
                        putExtra("recordTime", recordTime)
                        startActivity(this)
                    }
                }
            }

        }

        recordDetailViewModel.getAllRecordsDateByProgramNo(programNo)

    }
}
