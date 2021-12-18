package com.example.program.ui.record.sub

import android.os.Bundle
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityOneDayRecordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OneDayRecordActivity :
    BaseActivity<ActivityOneDayRecordBinding>(R.layout.activity_one_day_record) {

    private val oneDayRecordViewModel : OneDayRecordViewModel by viewModel()

    private lateinit var oneDayRecordAdapter: OneDayRecordAdapter

    private var programNo  = 0L
    private var recordTime: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        programNo = intent.getLongExtra("programNo", 0L)
        recordTime = intent.getStringExtra("recordTime")

        binding {
            oneDayRecordVm = oneDayRecordViewModel

            rvOneDayRecord.apply {
                setHasFixedSize(true)
                oneDayRecordAdapter = OneDayRecordAdapter()
                adapter = oneDayRecordAdapter

            }
        }

        oneDayRecordViewModel.getOneDayRecord(programNo, recordTime)


    }
}