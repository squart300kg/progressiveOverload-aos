package com.progressive.overload.ui.home.sub

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.progressive.overload.R
import com.progressive.overload.base.BaseActivity
import com.progressive.overload.databinding.ActivityRecordExerciseBinding
import com.progressive.overload.model.entity.RecordTable
import com.progressive.overload.model.model.ExerciseTypeModel
import com.progressive.overload.model.model.RecordExerciseModel
import com.progressive.overload.ui.dialog.CompleteDialog
import com.progressive.overload.util.DateUtil
import com.progressive.overload.util.GuideUtil
import com.securepreferences.SecurePreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecordExerciseActivity :
    BaseActivity<ActivityRecordExerciseBinding>(R.layout.activity_record_exercise) {

    @Inject
    lateinit var securePreferences: SecurePreferences

    private val recordExerciseViewModel: RecordExerciseViewModel by viewModels()

    private lateinit var recordExerciseAdapter: RecordExerciseAdapter

    private lateinit var exerciseModel: ExerciseTypeModel
    private var targetedDate: String? = null

    private var isPerformedExerciseAtLeastOneSet = false

    // 운동 종류 등록을 마친 후,
    private val onResultForTimer = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result?.resultCode == Activity.RESULT_OK) {
            val model = result.data?.getSerializableExtra("recordModel") as RecordExerciseModel
            Log.i("back", "recordModel: $model")

            // 운동 기록 저장
            recordExerciseViewModel.record(
                RecordTable(
                    name = exerciseModel.name,
                    weight = model.weight,
                    repitition = model.repitition,
                    setNum = model.no,
                    restTime = model.restTime,
                    rpe = model.rpe,
                    recordTime = DateUtil.getCurrentDateForRecord(),
                    programNo = exerciseModel.programNo,
                    exerciseTypeNo = exerciseModel.no
                )
            ) {
                Log.i("back", "${exerciseModel.name} success")
                recordExerciseAdapter.successExercise()
                isPerformedExerciseAtLeastOneSet = true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exerciseModel = intent.getSerializableExtra("exTypeModel") as ExerciseTypeModel
        targetedDate = intent.getStringExtra("targetedDate")

        Log.i("getTargetDate", "onCreate : $targetedDate")

        // 튜토리얼테스트
//        GuideUtil.saveOneSetClearGuideShown(securePreferences, false)

        if (!GuideUtil.isOneSetClearGuideShown(securePreferences)) {
            dataBinding.layoutOneSetClearGuide.root.isVisible = true
        }

        binding {
            recordExVm = recordExerciseViewModel

            layoutOneSetClearGuide.root.setOnClickListener {
                dataBinding.layoutOneSetClearGuide.root.isVisible = false
                GuideUtil.saveOneSetClearGuideShown(securePreferences, true)
            }

            tvGoPrevious.setOnClickListener {
                goPrevious()
            }

            tvGoNext.setOnClickListener {
                goNext()
            }

            tvDate.text = targetedDate

            rvRecordEx.apply {
                setHasFixedSize(true)
                recordExerciseAdapter =
                    RecordExerciseAdapter(this@RecordExerciseActivity,
                        { model -> // 운동 수행 완료

                            when {
                                model.weight == 0.0f -> {
                                    Toast.makeText(this@RecordExerciseActivity,
                                        "'중량' 입력란이 '0'입니다!",
                                        Toast.LENGTH_SHORT).show()
                                }
                                model.repitition == 0 -> {
                                    Toast.makeText(this@RecordExerciseActivity,
                                        "'반복수' 입력란이 '0'입니다!",
                                        Toast.LENGTH_SHORT).show()
                                }
                                model.restTime == 0 -> {
                                    Toast.makeText(this@RecordExerciseActivity,
                                        "'휴식시간' 입력란이 '0'입니다!",
                                        Toast.LENGTH_SHORT).show()
                                }
                                else -> {
                                    // 쉬는시간 타이머 시작
                                    Intent(this@RecordExerciseActivity,
                                        TimerActivity::class.java).apply {
                                        putExtra("recordModel", model)
                                        onResultForTimer.launch(this)
                                    }
                                }
                            }
                        },
                        { // 해당 운동의 모든 세트를 완료함
                            CompleteDialog.newInstance(exerciseModel.name) {
                                setResult(RESULT_OK)
                                finish()
                            }.show(
                                supportFragmentManager,
                                "completeDialogTag"
                            )
                        })
                adapter = recordExerciseAdapter

            }
        }

        recordExerciseViewModel.getTargetedExercisePerformed(
            exerciseModel.programNo,
            exerciseModel.no,
        ) { recordTable ->
            recordExerciseViewModel.initExercise(exerciseModel, recordTable)
        }

        initBannerAd(dataBinding.adView)

        // TODO 이전기록, 이후기록 불러오기 기능 추후 오픈시 주석 해제
//        recordExerciseViewModel.getTargetedExercisePerformed(
//            exerciseModel.programNo,
//            exerciseModel.no,
//            targetedDate
//        ) { recordTable ->
//            recordExerciseViewModel.initExercise(exerciseModel, recordTable)
//        }
//
//        recordExerciseViewModel.getTargetedAllDate(exerciseModel.programNo, exerciseModel.no)
//
//        recordExerciseViewModel.getPreviousDate(
//            exerciseModel.programNo,
//            exerciseModel.no,
//            targetedDate
//        ) { previousDate ->
//            // 이전 데이터가 없다면 '이전 기록' gone처리
//            dataBinding.tvGoPrevious.isVisible = previousDate != null
//        }
//
//        recordExerciseViewModel.getNextDate(
//            exerciseModel.programNo,
//            exerciseModel.no,
//            targetedDate
//        ) { nextDate ->
//            // 이후 기록이 없고 오늘 날짜가 맞다면 '이후 기록' gone 처리
//            dataBinding.tvGoNext.isVisible =
//                !(nextDate == null && targetedDate == DateUtil.getCurrentDateForRecord())
//        }
    }

    private fun goNext() {
        recordExerciseViewModel.getNextDate(
            exerciseModel.programNo,
            exerciseModel.no,
            targetedDate
        ) { nextDate ->
            if (nextDate == null) {
                if (targetedDate != DateUtil.getCurrentDateForRecord()) {
                    Intent(this, RecordExerciseActivity::class.java).apply {
                        putExtra("exTypeModel", exerciseModel)
                        putExtra("targetedDate", DateUtil.getCurrentDateForRecord())
                        startActivity(this)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        finish()
                    }
                }
            } else {
                Intent(this, RecordExerciseActivity::class.java).apply {
                    putExtra("exTypeModel", exerciseModel)
                    putExtra("targetedDate", nextDate)
                    startActivity(this)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }
            }
        }
    }

    private fun goPrevious() {
        recordExerciseViewModel.getPreviousDate(
            exerciseModel.programNo,
            exerciseModel.no,
            targetedDate
        ) { previousDate ->
            if (previousDate == null) {
                Toast.makeText(this, "이전 운동 기록이 없습니다!", Toast.LENGTH_SHORT).show()
            } else {
                Intent(this, RecordExerciseActivity::class.java).apply {
                    putExtra("exTypeModel", exerciseModel)
                    putExtra("targetedDate", previousDate)
                    startActivity(this)
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    finish()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (isPerformedExerciseAtLeastOneSet) {
            setResult(RESULT_OK)
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun showInternetDisconnectedView(disconnected: Boolean) {
        dataBinding.viewNetworkNotConnected.root.isVisible = disconnected
    }

}