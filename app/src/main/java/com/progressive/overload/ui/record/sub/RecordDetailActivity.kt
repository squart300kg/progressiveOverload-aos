package com.progressive.overload.ui.record.sub

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.progressive.overload.R
import com.progressive.overload.base.BaseActivity
import com.progressive.overload.databinding.ActivityRecordDetailBinding
import com.progressive.overload.util.Ad.FullScreenAdCallback
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.progressive.overload.util.Ad.AdUtil
import com.progressive.overload.util.XaxisDateFormatter
import com.progressive.overload.util.YaxisVolumeFormatter
import com.securepreferences.SecurePreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecordDetailActivity :
    BaseActivity<ActivityRecordDetailBinding>(R.layout.activity_record_detail), FullScreenAdCallback {

    private val recordDetailViewModel: RecordDetailViewModel by viewModels()

    @Inject
    lateinit var securePreferences: SecurePreferences

    private lateinit var recordsAdapter: RecordsAdapter

    private var mInterstitialAd: InterstitialAd? = null

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

        initBannerAd(dataBinding.adView)

        initFullScreenAd(this)
    }

    override fun onBackPressed() {
        finish()
        if (AdUtil.isTurnToExposeAd(securePreferences))
            startFullScreenAd()
    }

    override fun onCloseFullScreenAd() {
//        finish()
    }
}
