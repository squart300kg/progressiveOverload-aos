package com.example.program.ui.home.sub

import android.content.Intent
import android.os.Bundle
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivitySplitSelectionBinding

class SplitSelectionActivity: BaseActivity<ActivitySplitSelectionBinding>(R.layout.activity_split_selection) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            rvSplitSelection.apply {
                setHasFixedSize(true)
                adapter = SplitSelectionAdapter { split ->
                    Intent(this@SplitSelectionActivity, RegExcersizeTypeActivity::class.java).apply {
                        putExtra("split", split)
                        startActivity(this)
                    }
                }

            }
        }

    }
}