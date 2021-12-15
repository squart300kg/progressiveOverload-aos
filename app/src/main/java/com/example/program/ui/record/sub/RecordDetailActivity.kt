package com.example.program.ui.record.sub

import android.os.Bundle
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityRecordDetailBinding
import com.example.program.model.model.RecordModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecordDetailActivity :
    BaseActivity<ActivityRecordDetailBinding>(R.layout.activity_record_detail) {

    private val recordDetailViewModel : RecordDetailViewModel by viewModel()

    private var programNo: Long = 0L

    private var totalVolumeCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        programNo = intent.getLongExtra("programNo", 0L)

        binding {
            recordDetailVm = recordDetailViewModel

            rvRecord.apply {
                setHasFixedSize(true)
                adapter = RecordsAdapter(this@RecordDetailActivity)
            }

        }

        recordDetailViewModel.getAllRecordsDateByProgramNo(programNo) { dates ->
            val records = mutableListOf<RecordModel>()

            dates.forEachIndexed { index, date ->
                val recordModel = RecordModel(
                    0,
                    date
                )

                recordDetailViewModel.getTargetDateTotalVolume(programNo, date) { volume ->
                    totalVolumeCount++
                    recordDetailViewModel.initRecord(volume, date)
                }
            }
        }
    }
}
