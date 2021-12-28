package com.example.program.ui.record.sub

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityRecordDetailBinding
import com.example.program.util.DateUtil
import com.example.program.util.MyFormatter
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

    private lateinit var recordsAdapter: RecordsAdapter

    private var programNo: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        programNo = intent.getLongExtra("programNo", 0L)

        binding {
            recordDetailVm = recordDetailViewModel

            chart.apply {
                val entries = arrayListOf<Entry>()
                for (index in 0 until 10) {
                    entries.add(
                        Entry(index.toFloat(), index.toFloat())
                    )
                }

                LineDataSet(entries, "속성명1").apply {
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

                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    textColor = Color.BLACK
                    valueFormatter = MyFormatter()
                    granularity = 1f
                    enableGridDashedLine(8f, 24f, 0f)
                }

                axisLeft.apply {
                    textColor = Color.BLACK
                }

                axisRight.apply {
                    setDrawLabels(false)
                    setDrawAxisLine(false)
                    setDrawGridLines(false)
                }

                description.text = "hello~!!"

                isDoubleTapToZoomEnabled = false
                setDrawGridBackground(false)
                animateY(2000, Easing.EasingOption.EaseInCubic);
                invalidate()

//                val marker = RecordMarkerView(this@RecordDetailActivity, R.layout.layout_graph_marker)
//                marker.chartView = chart
//                chart.marker = marker
            }

            rvRecord.apply {
                setHasFixedSize(true)
                recordsAdapter = RecordsAdapter(this@RecordDetailActivity,
                    { recordTime -> // 자세히 보기
                        recordDetailViewModel.getExerciseVolumes(programNo, recordTime) { exerciseVolumes ->
                            recordsAdapter.loadExerciseVolumes(exerciseVolumes)
                        }
                    },
                    { recordTime -> // 기록 상세 보기
                        Intent(this@RecordDetailActivity, OneDayRecordActivity::class.java).apply {
                            putExtra("programNo", programNo)
                            putExtra("recordTime", recordTime)
                            startActivity(this)
                        }
                    },
                    { position -> // 접기
                        scrollToPosition(position)
                    })
                adapter = recordsAdapter
            }

        }

        recordDetailViewModel.getAllRecordsDateByProgramNo(programNo)

    }
}
