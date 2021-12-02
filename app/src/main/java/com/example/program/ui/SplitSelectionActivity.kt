package com.example.program.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivitySplitSelectionBinding
import com.example.program.util.ItemDecoration

class SplitSelectionActivity: BaseActivity<ActivitySplitSelectionBinding>(R.layout.activity_split_selection) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            rvSplitSelection.apply {
                setHasFixedSize(true)
                adapter = SplitSelectionAdapter()

            }
        }

    }
}