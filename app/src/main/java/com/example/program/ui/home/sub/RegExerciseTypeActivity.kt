package com.example.program.ui.home.sub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityExcerciseTypeRegistrationBinding
import com.example.program.ui.dialog.CancelDialog
import com.example.program.ui.dialog.RegisterDialog
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegExerciseTypeActivity: BaseActivity<ActivityExcerciseTypeRegistrationBinding>(R.layout.activity_excercise_type_registration) {

    private val regExerciseTypeViewModel : RegExerciseTypeViewModel by viewModel()

    private var selectedSplitIndex : Int? = 0

    private var exercisesSize = 0

    private var splitCount : Int? = null
    private var splitText : String? = null
    private var programNo : Long? = null

    private lateinit var cancelDialog : CancelDialog
    private lateinit var registerDialog : RegisterDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splitCount = intent.getIntExtra("splitCount", -1)
        splitText = intent.getStringExtra("splitText")
        programNo = intent.getLongExtra("programNo", 0L)
        if (intent.getBooleanExtra("isIntentToExercise", false)) {
            dataBinding.layoutAddExerciseType.isVisible = false
            dataBinding.tvExerciseStart.isVisible = true
        }

        binding {
            regVm = regExerciseTypeViewModel

            tlSplit.apply {
                for (i in 0 until splitCount!!) {
                    tlSplit.addTab(tlSplit.newTab().setText("${i + 1}" + "분할"))
                }

                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabUnselected(tab: TabLayout.Tab?) { }
                    override fun onTabReselected(tab: TabLayout.Tab?) { }
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        selectedSplitIndex = tab?.position

                        regExerciseTypeViewModel.getExercises(
                            programNo = programNo,
                            splitIndex = selectedSplitIndex
                        )
                    }
                })
            }

            layoutAddExerciseType.setOnClickListener {
                Intent(this@RegExerciseTypeActivity, RegExerciseTypeDetailActivity::class.java).apply {
                    putExtra("selectedSplitIndex", selectedSplitIndex)
                    putExtra("splitText", splitText)
                    putExtra("programNo", programNo)
                    startActivity(this)
                }
            }

            rvExercises.apply {
                setHasFixedSize(true)
                adapter = RegExerciseTypeAdapter {
                    Log.i("onClickExercise", it.toString())
                    Intent(this@RegExerciseTypeActivity, RegExerciseTypeDetailActivity::class.java).apply {
                        putExtra("isUpdate", true)
                        putExtra("selectedSplitIndex", selectedSplitIndex)
                        putExtra("exTypeTable", it)
                        putExtra("programNo", programNo)
                        startActivity(this)
                    }
                }
            }

            tvRegSuccess.setOnClickListener {
                when (exercisesSize > 0) {
                    true -> {
                        registerDialog = RegisterDialog.newInstance(programNo)
                        registerDialog.show(
                            supportFragmentManager,
                            registerDialog.tag
                        )
                    }
                    false -> Toast.makeText(this@RegExerciseTypeActivity, "운동 종류를 등록해 주세요!", Toast.LENGTH_LONG).show()
                }
            }

            tvExerciseStart.setOnClickListener {
                Toast.makeText(this@RegExerciseTypeActivity, "오늘 운동 시작!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        regExerciseTypeViewModel.getExercises(
            programNo = programNo,
            splitIndex = selectedSplitIndex
        ) { size ->
            exercisesSize = size
            if (exercisesSize > 0)
                dataBinding.tvRegSuccess.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
        }
    }

    override fun onBackPressed() {
        cancelDialog = CancelDialog.newInstance(
            {
                regExerciseTypeViewModel.deleteProgram(
                    programNo
                ) {
                    super.onBackPressed()
                }
            },
            {
                super.onBackPressed()
            }
        )
        cancelDialog.show(
            supportFragmentManager,
            cancelDialog.tag
        )
    }

}