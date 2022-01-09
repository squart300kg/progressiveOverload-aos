package com.progressive.overload.ui.home.sub

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.viewModels
import com.progressive.overload.R
import com.progressive.overload.base.BaseActivity
import com.progressive.overload.databinding.ActivityTimerBinding
import com.progressive.overload.model.model.RecordExerciseModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimerActivity :
    BaseActivity<ActivityTimerBinding>(R.layout.activity_timer) {

    private val timerViewModel: TimerViewModel by viewModels()

    private lateinit var recordModel: RecordExerciseModel

    private lateinit var countDownTimer: CountDownTimer

    private fun countDownTimer(maxTime: Int) =
        object : CountDownTimer((maxTime).toLong(), 50) {
            override fun onFinish() {
                onBackPressed()
            }
            override fun onTick(millisUntilFinished: Long) {
                timerViewModel.initTimerNumber(millisUntilFinished.toInt())
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recordModel = intent.getSerializableExtra("recordModel") as RecordExerciseModel

        // 카운트다운 시작
        timerViewModel.initMaxTime(recordModel.restTime * 1000)
        countDownTimer = countDownTimer(recordModel.restTime * 1000).start()

        binding {
            timerVm = timerViewModel

            // 타이머 스킵
            tvSkip.setOnClickListener {
                countDownTimer.cancel()
                onBackPressed()
            }

            // 타이머 취소
            tvCancel.setOnClickListener {
                countDownTimer.cancel()
                finish()
            }
        }

        initBannerAd(dataBinding.adView)

    }

    override fun onBackPressed() {
        Intent(this, RecordExerciseActivity::class.java).apply {
            putExtra("recordModel", recordModel)
            setResult(RESULT_OK, this)
        }
        super.onBackPressed()
    }
}