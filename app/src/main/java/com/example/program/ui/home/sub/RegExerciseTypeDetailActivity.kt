package com.example.program.ui.home.sub

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextThemeWrapper
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityExcerciseTypeRegistrationDetailBinding
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.model.ExerciseTypeModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegExerciseTypeDetailActivity :
    BaseActivity<ActivityExcerciseTypeRegistrationDetailBinding>(R.layout.activity_excercise_type_registration_detail) {

    private val viewModel: RegExerciseTypeViewModel by viewModels()

    private var selectedSplitIndex: Int? = null
    private var programNo: Long? = null

    private lateinit var exerciseTypeModel: ExerciseTypeModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectedSplitIndex = intent.getIntExtra("selectedSplitIndex", 0)
        programNo = intent.getLongExtra("programNo", 0L)
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

            tvRegister.setOnClickListener {
                if (isInputNotFull()) {
                    Toast.makeText(
                        this@RegExerciseTypeDetailActivity,
                        "운동 정보를 모두 입력해 주세요!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    viewModel.insertExerciseType(
                        ExerciseTypeTable(
                            name = layoutExerciseType.etExerciseType.text.toString(),
                            weight = layoutWeight.etWeight.text.toString().toInt(),
                            repitition = layoutRepitition.etRepitition.text.toString().toInt(),
                            setNum = layoutSetNumber.etSetNumber.text.toString().toInt(),
                            restTime = layoutRestTime.etRestTime.text.toString().toInt(),
                            programNo = programNo,
                            splitTypeIndex = selectedSplitIndex
                        )
                    )
                    Toast.makeText(
                        this@RegExerciseTypeDetailActivity,
                        "운동 등록을 완료하였습니다!",
                        Toast.LENGTH_LONG
                    ).show()
                    setResult(RESULT_OK)
                    finish()
                }
            }

            tvDelete.setOnClickListener {
                val errorDialog = AlertDialog.Builder(ContextThemeWrapper(
                    this@RegExerciseTypeDetailActivity,
                    R.style.AlertDialogCustom))
                    .setCancelable(true)
                    .setMessage("정말 삭제하시겠습니까?\n\n해당 운동을 삭제하면 해당 운동의 기록도 모두 삭제됩니다.")
                    .setPositiveButton("삭제") { dialog, _ ->
                        viewModel.deleteExercise(exerciseTypeModel) {
                            setResult(RESULT_OK)
                            finish()
                        }
                        dialog.dismiss()
                    }
                    .setNegativeButton("취소") { _, _ -> }
                    .create()
                errorDialog.show()
            }

            tvCancel.setOnClickListener {
                onBackPressed()
            }

            tvUpdate.setOnClickListener {
                viewModel.updateExercise(
                    ExerciseTypeTable(
                        no = exerciseTypeModel.no,
                        name = layoutExerciseType.etExerciseType.text.toString(),
                        weight = layoutWeight.etWeight.text.toString().toInt(),
                        repitition = layoutRepitition.etRepitition.text.toString().toInt(),
                        setNum = layoutSetNumber.etSetNumber.text.toString().toInt(),
                        restTime = layoutRestTime.etRestTime.text.toString().toInt(),
                        programNo = programNo,
                        splitTypeIndex = selectedSplitIndex
                    )
                ) {
                    setResult(RESULT_OK)
                    finish()
                }
            }

            layoutExerciseType.etExerciseType.apply {
                checkIfInputIsFull()
            }

            layoutRepitition.etRepitition.apply {
                checkIfInputIsFull()
            }

            layoutRestTime.etRestTime.apply {
                checkIfInputIsFull()
            }

            layoutSetNumber.etSetNumber.apply {
                checkIfInputIsFull()
            }

            layoutWeight.etWeight.apply {
                checkIfInputIsFull()
            }
        }
    }

    private fun isInputNotFull(): Boolean {
        return dataBinding.layoutExerciseType.etExerciseType.text.isNullOrEmpty() ||
                dataBinding.layoutRepitition.etRepitition.text.isNullOrEmpty() ||
                dataBinding.layoutRestTime.etRestTime.text.isNullOrEmpty() ||
                dataBinding.layoutSetNumber.etSetNumber.text.isNullOrEmpty() ||
                dataBinding.layoutWeight.etWeight.text.isNullOrEmpty()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun EditText.checkIfInputIsFull() {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isInputNotFull()) {
                    dataBinding.tvRegister.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.grey
                        )
                    )
                } else {
                    dataBinding.tvRegister.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.black
                        )
                    )
                }
            }
        })
    }
}