package com.example.program.ui.record.sub

import android.os.Bundle
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityOneDayRecordBinding

class OneDayRecordActivity :
    BaseActivity<ActivityOneDayRecordBinding>(R.layout.activity_one_day_record) {

    private var programNo  = 0L
    private lateinit var recordTime: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}