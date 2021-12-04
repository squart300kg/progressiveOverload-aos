package com.example.program.ui.home.sub

import android.content.Intent
import android.os.Bundle
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityExcerciseTypeRegistrationBinding

class RegExcersizeTypeActivity: BaseActivity<ActivityExcerciseTypeRegistrationBinding>(R.layout.activity_excercise_type_registration) {

    private var split = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        split = intent.getIntExtra("split", -1)

        binding {
            tlChannel.apply {
                for (i in 0 until split) {
                    tlChannel.addTab(tlChannel.newTab().setText("${i + 1}" + "분할"))
                }
            }

            layoutAddExerciseType.setOnClickListener {
                Intent(this@RegExcersizeTypeActivity, RegExcersizeTypeDetailActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }

    }
}