package com.progressive.overload.ui.home.sub

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.progressive.overload.R
import com.progressive.overload.base.BaseActivity
import com.progressive.overload.databinding.ActivityMicroCycleSplitSelectionBinding
import com.progressive.overload.model.entity.ProgramTable
import com.progressive.overload.util.DateUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MicroCycleSelectionActivity :
    BaseActivity<ActivityMicroCycleSplitSelectionBinding>(R.layout.activity_micro_cycle_split_selection) {

    private val splitSelectionViewModel: SplitSelectionViewModel by viewModels()

    private var mesoSplitCount = 0
    private var mesoSplitText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mesoSplitCount = intent.getIntExtra("mesoSplitCount", 0)
        mesoSplitText = intent.getStringExtra("mesoSplitText").toString()

        binding {
            rvSplitSelection.apply {
                setHasFixedSize(true)
                adapter = MicroCycleSelectionAdapter { microSplit ->
                    splitSelectionViewModel.insertProgram(
                        ProgramTable(
                            name = DateUtil.getCurrentDateForProgramName(),
                            mesoSplitText = mesoSplitText,
                            mesoSplitCount = mesoSplitCount,
                            microCycleText = microSplit.text,
                            microCycleCount = microSplit.count
                        )
                    ) { programNo ->
                        Intent(this@MicroCycleSelectionActivity,
                            ExerciseTypeActivity::class.java).apply {
                            putExtra("programNo", programNo)
                            putExtra("mesoCycleSplitCount", mesoSplitCount)
                            putExtra("mesoCycleSplitText", mesoSplitText)
                            putExtra("microCycleSplitCount", microSplit.count)
                            putExtra("microCycleSplitText", microSplit.text)
                            startActivity(this)
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        }
                    }
                }

            }
        }

        initBannerAd(dataBinding.adView)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}