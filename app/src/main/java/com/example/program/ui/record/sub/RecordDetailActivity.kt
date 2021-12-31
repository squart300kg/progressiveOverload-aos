package com.example.program.ui.record.sub

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityRecordDetailBinding
import com.example.program.util.XaxisDateFormatter
import com.example.program.util.YaxisVolumeFormatter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordDetailActivity :
    BaseActivity<ActivityRecordDetailBinding>(R.layout.activity_record_detail) {

    private val recordDetailViewModel: RecordDetailViewModel by viewModels()

    private lateinit var recordsAdapter: RecordsAdapter

    private var programNo: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        programNo = intent.getLongExtra("programNo", 0L)

        binding {
            recordDetailVm = recordDetailViewModel

            chart.apply {

                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    textColor = Color.BLACK
                    granularity = 1f // 1일
                    valueFormatter = XaxisDateFormatter()
                }

                axisLeft.apply {
                    textColor = Color.BLACK
                    granularity = 1f // 1KG
                    valueFormatter = YaxisVolumeFormatter()
                }

                axisRight.apply {
                    setDrawLabels(false)
                    setDrawAxisLine(false)
                    setDrawGridLines(false)
                }

                description.text = ""

                isDoubleTapToZoomEnabled = true
                setDrawGridBackground(false)
                setNoDataText("운동 기록이 없습니다.")
                setNoDataTextColor(Color.BLUE)
                animateY(2000, Easing.EasingOption.EaseInCubic)
                invalidate()

//                val marker = RecordMarkerView(this@RecordDetailActivity, R.layout.layout_graph_marker)
//                marker.chartView = chart
//                chart.marker = marker

//                RecordMarkerView(this@RecordDetailActivity, R.layout.layout_graph_marker).apply {
//                    chartView = chart
//                    chart.marker = this
//                }
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
