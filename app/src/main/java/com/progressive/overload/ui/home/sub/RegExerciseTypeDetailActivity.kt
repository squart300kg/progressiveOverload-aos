package com.progressive.overload.ui.home.sub

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextThemeWrapper
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.progressive.overload.R
import com.progressive.overload.base.BaseActivity
import com.progressive.overload.databinding.ActivityExcerciseTypeRegistrationDetailBinding
import com.progressive.overload.model.entity.ExerciseTypeTable
import com.progressive.overload.model.model.ExerciseTypeModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegExerciseTypeDetailActivity :
    BaseActivity<ActivityExcerciseTypeRegistrationDetailBinding>(R.layout.activity_excercise_type_registration_detail) {

    private val viewModel: RegExerciseTypeViewModel by viewModels()

    private var programNo: Long = 0L
    private var mesoCycleSplitIndex = 0
    private var microCycleSplitIndex = 0

    private lateinit var exerciseTypeModel: ExerciseTypeModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        programNo = intent.getLongExtra("programNo", 0L)
        mesoCycleSplitIndex = intent.getIntExtra("mesoCycleSplitIndex", 0)
        microCycleSplitIndex = intent.getIntExtra("microCycleSplitIndex", 0)
        if (intent.getBooleanExtra("isUpdate", false)) {
            exerciseTypeModel = intent.getSerializableExtra("exTypeModel") as ExerciseTypeModel
            viewModel.setExerciseInfo(exerciseTypeModel)
            dataBinding.tvRegister.isVisible = false
            dataBinding.layoutCancelOrUpdate.isVisible = true
        }

        binding {
            layoutExerciseType.exerciseVm = viewModel
            layoutWeight.exerciseVm = viewModel
            layoutRepitition.exerciseVm = viewModel
            layoutSetNumber.exerciseVm = viewModel
            layoutRestTime.exerciseVm = viewModel
            layoutRpe.exerciseVm = viewModel

            tvRegister.setOnClickListener {
                if (isInputNotFull()) {
                    Toast.makeText(
                        this@RegExerciseTypeDetailActivity,
                        "운동 정보를 모두 입력해 주세요!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {

                    // 운동 등록
                    viewModel.insertExerciseType(
                        ExerciseTypeTable(
                            name = layoutExerciseType.etExerciseType.text.toString(),
                            weight = layoutWeight.etWeight.text.toString().toFloat(),
                            repitition = layoutRepitition.etRepitition.text.toString().toInt(),
                            setNum = layoutSetNumber.etSetNumber.text.toString().toInt(),
                            restTime = layoutRestTime.etRestTime.text.toString().toInt(),
                            rpe = layoutRpe.etRpe.text.toString().toInt(),
                            programNo = programNo,
                            mesoCycleSplitIndex = mesoCycleSplitIndex,
                            microCycleSplitIndex = microCycleSplitIndex
                        )
                    ) {
                        Toast.makeText(
                            this@RegExerciseTypeDetailActivity,
                            "운동 등록 완료!",
                            Toast.LENGTH_LONG
                        ).show()
                        setResult(RESULT_OK)
                        finish()
                    }
                }
            }

            tvCancel.setOnClickListener {
                onBackPressed()
            }

            tvUpdate.setOnClickListener {
                viewModel.updateExercise(
                    ExerciseTypeTable(
                        no = exerciseTypeModel.no,
                        name = layoutExerciseType.etExerciseType.text.toString(),
                        weight = layoutWeight.etWeight.text.toString().toFloat(),
                        repitition = layoutRepitition.etRepitition.text.toString().toInt(),
                        setNum = layoutSetNumber.etSetNumber.text.toString().toInt(),
                        restTime = layoutRestTime.etRestTime.text.toString().toInt(),
                        rpe = layoutRpe.etRpe.text.toString().toInt(),
                        programNo = programNo,
                        mesoCycleSplitIndex = mesoCycleSplitIndex,
                        microCycleSplitIndex = microCycleSplitIndex
                    )
                ) {
                    setResult(RESULT_OK)
                    finish()
                }
            }

            layoutExerciseType.etExerciseType.apply {
                checkIfInputIsFull(this)
            }

            layoutRepitition.etRepitition.apply {
                checkIfInputIsFull(this)
            }

            layoutRestTime.etRestTime.apply {
                checkIfInputIsFull(this)
            }

            layoutSetNumber.etSetNumber.apply {
                checkIfInputIsFull(this)
            }

            layoutWeight.etWeight.apply {
                checkIfInputIsFull(this)
            }

            layoutRpe.etRpe.apply {
                checkIfInputIsFull(this)
            }
        }

        initBannerAd(dataBinding.adView)
    }

    private fun isInputNotFull(): Boolean {
        return dataBinding.layoutExerciseType.etExerciseType.text.isNullOrEmpty() ||
                dataBinding.layoutRepitition.etRepitition.text.isNullOrEmpty() ||
                dataBinding.layoutRestTime.etRestTime.text.isNullOrEmpty() ||
                dataBinding.layoutSetNumber.etSetNumber.text.isNullOrEmpty() ||
                dataBinding.layoutSetNumber.etSetNumber.text.isNullOrEmpty() ||
                dataBinding.layoutRpe.etRpe.text.isNullOrEmpty()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun EditText.checkIfInputIsFull(et: EditText) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                dataBinding.tvRegister.isSelected = !isInputNotFull()

                // 전부다 입력했고,
                if (!isInputNotFull()) {

                    // 포커스가 RIR에 있을시,
                    if (dataBinding.layoutRpe.etRpe.hasFocus()) {

                            // 키보드 자동 내림
                            (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                                dataBinding.layoutRpe.etRpe.windowToken,
                                0);
                        }

                }
            }
        })
    }

    override fun showInternetDisconnectedView(disconnected: Boolean) {
        dataBinding.viewNetworkNotConnected.root.isVisible = disconnected
    }
}