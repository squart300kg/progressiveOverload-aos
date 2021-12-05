package com.example.program.ui.home.sub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivitySplitSelectionBinding
import com.example.program.model.entity.ProgramTable
import com.example.program.util.DateUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplitSelectionActivity: BaseActivity<ActivitySplitSelectionBinding>(R.layout.activity_split_selection) {

    private val splitSelectionViewModel : SplitSelectionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            rvSplitSelection.apply {
                setHasFixedSize(true)
                adapter = SplitSelectionAdapter { split ->
                    splitSelectionViewModel.insertProgram(
                        ProgramTable(
                            name = DateUtil.getCurrentDateForProgramName(),
                            splitText = split.text,
                            splitCount = split.count
                        )
                    ) { programNo ->
                        Intent(this@SplitSelectionActivity, RegExerciseTypeActivity::class.java).apply {
                            putExtra("splitCount", split.count)
                            putExtra("splitText", split.text)
                            putExtra("programNo", programNo)
                            startActivity(this)
                        }
                    }
                }

            }
        }
    }
}