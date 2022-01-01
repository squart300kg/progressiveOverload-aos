package com.example.program.ui.record.sub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityOneDayRecordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OneDayRecordActivity :
    BaseActivity<ActivityOneDayRecordBinding>(R.layout.activity_one_day_record) {

    private val oneDayRecordViewModel: OneDayRecordViewModel by viewModels()

    private lateinit var oneDayRecordAdapter: OneDayRecordAdapter

    private var programNo = 0L
    private var recordTime: String? = null

    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("OneDayRecordActivity", "onCreate")

        programNo = intent.getLongExtra("programNo", 0L)
        recordTime = intent.getStringExtra("recordTime")

        binding {
            oneDayRecordVm = oneDayRecordViewModel

            gestureDetector = GestureDetector(this@OneDayRecordActivity,
                object : GestureDetector.OnGestureListener {
                    override fun onDown(p0: MotionEvent?) = true
                    override fun onLongPress(p0: MotionEvent?) { }
                    override fun onShowPress(p0: MotionEvent?) { }
                    override fun onSingleTapUp(p0: MotionEvent?) = true
                    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float, ) = true
                    override fun onFling(
                        e1: MotionEvent?,
                        e2: MotionEvent?,
                        velocityX: Float,
                        velocityY: Float,
                    ): Boolean {

                        // 스크롤 기울기를 구함
                        var inclincation = 0.0f
                        if (e1 != null && e2 != null) {
                            inclincation = Math.abs((e2.y - e1.y) / (e2.x - e1.x))

                            Log.i("gesture",
                                "onFling호출\n " +
                                        "e1(x, y) : (${e1.x}, ${e1.y})\n " +
                                        "e2(x, y) : (${e2.x}, ${e2.y})\n " +
                                        "inclincation : $inclincation\n " +
                                        "distance(X, Y) : ($velocityX, $velocityY)")

                            // 기울기가 1/2보다ㅁ 작을 경우만 옆으로 스크롤
                            if (inclincation < 0.5) {
                                if (velocityX > 0) {
                                    goPrevious()
                                } else {
                                    goNext()
                                }
                            }
                        }

                        return true
                    }
                })

//            rvOneDayRecord.setOnTouchListener { view, event ->
//                gestureDetector.onTouchEvent(event)
//                true
//            }

            tvGoPrevious.setOnClickListener {
                goPrevious()
            }

            tvGoNext.setOnClickListener {
                goNext()
            }

            tvDate.text = recordTime

            rvOneDayRecord.apply {
                setHasFixedSize(true)
                oneDayRecordAdapter = OneDayRecordAdapter(
                    { name -> // 자세히보기
                        oneDayRecordViewModel.getOneDayRecord(name, programNo, recordTime) { records ->
                            oneDayRecordAdapter.loadOneDayRecordDetail(records)
                        }
                    },
                    { position -> // 접기
                        scrollToPosition(position)
                    }
                )
                adapter = oneDayRecordAdapter
            }
        }
        oneDayRecordViewModel.getOneDayRecordName(programNo, recordTime)

        // TODO 이전기록, 이후기록 불러오기 기능 추후 오픈시 주석 해제
//        oneDayRecordViewModel.getPreviousDate(
//            programNo,
//            recordTime
//        ) { previousDate ->
//            if (previousDate == null)
//                dataBinding.tvGoPrevious.isVisible = false
//            else
//                dataBinding.tvGoPrevious.isVisible = true
//        }
//
//        oneDayRecordViewModel.getNextDate(
//            programNo,
//            recordTime
//        ) { nextDate ->
//            if (nextDate == null)
//                dataBinding.tvGoNext.isVisible = false
//            else
//                dataBinding.tvGoNext.isVisible = true
//        }


    }

    private fun goPrevious() {
        oneDayRecordViewModel.getPreviousDate(
            programNo,
            recordTime
        ) { previousDate ->
            Intent(this, OneDayRecordActivity::class.java).apply {
                putExtra("programNo", programNo)
                putExtra("recordTime", previousDate)
                startActivity(this)
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                finish()
            }
        }
    }

    private fun goNext() {
        oneDayRecordViewModel.getNextDate(
            programNo,
            recordTime
        ) { previousDate ->
            Intent(this, OneDayRecordActivity::class.java).apply {
                putExtra("programNo", programNo)
                putExtra("recordTime", previousDate)
                startActivity(this)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                finish()
            }
        }
    }
}