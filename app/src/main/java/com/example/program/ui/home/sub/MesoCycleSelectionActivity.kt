package com.example.program.ui.home.sub

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityMesoCycleSplitSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MesoCycleSelectionActivity :
    BaseActivity<ActivityMesoCycleSplitSelectionBinding>(R.layout.activity_meso_cycle_split_selection) {

    private val splitSelectionViewModel: SplitSelectionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            rvSplitSelection.apply {
                setHasFixedSize(true)
                adapter = MesoCycleSelectionAdapter { split ->
                    Intent(this@MesoCycleSelectionActivity, MicroCycleSelectionActivity::class.java).apply {
                        putExtra("mesoSplitCount", split.count)
                        putExtra("mesoSplitText", split.text)
                        startActivity(this)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    }


//                    mesoCycleSelectionViewModel.insertProgram(
//                        ProgramTable(
//                            name = DateUtil.getCurrentDateForProgramName(),
//                            mesoSplitText = split.text,
//                            mesoSplitCount = split.count
//                        )
//                    )
//                    { programNo ->
//
//                        Intent(this@MesoCycleSelectionActivity,
//                            ExerciseTypeActivity::class.java).apply {
//                            putExtra("splitCount", split.count)
//                            putExtra("splitText", split.text)
//                            putExtra("programNo", programNo)
//                            startActivity(this)
//                        }
//                    }
                }

            }
        }
    }
}