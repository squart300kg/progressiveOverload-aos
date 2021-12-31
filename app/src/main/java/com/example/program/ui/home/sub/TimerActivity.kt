package com.example.program.ui.home.sub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityTimerBinding
import com.example.program.model.model.RecordExerciseModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimerActivity :
    BaseActivity<ActivityTimerBinding>(R.layout.activity_timer) {

    private lateinit var recordModel: RecordExerciseModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recordModel = intent.getSerializableExtra("recordModel") as RecordExerciseModel


    }

    override fun onBackPressed() {
        Intent(this, RecordExerciseActivity::class.java).apply {
            putExtra("recordModel", recordModel)
            setResult(RESULT_OK, this)
        }
        super.onBackPressed()
    }
}