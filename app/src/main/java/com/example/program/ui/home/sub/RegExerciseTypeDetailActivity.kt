package com.example.program.ui.home.sub

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityExcerciseTypeRegistrationDetailBinding
import com.example.program.model.entity.ExerciseTypeTable
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegExerciseTypeDetailActivity: BaseActivity<ActivityExcerciseTypeRegistrationDetailBinding>(R.layout.activity_excercise_type_registration_detail) {

    private val regExerciseTypeViewModel : RegExerciseTypeViewModel by viewModel()

    private var selectedSplitIndex : Int? = null
    private var programNo : Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectedSplitIndex = intent.getIntExtra("selectedSplitIndex", 0)
        programNo = intent.getLongExtra("programNo", 0L)

        binding {
            tvRegister.setOnClickListener {
                if (isInputNotFull()) {
                    Toast.makeText(this@RegExerciseTypeDetailActivity, "운동 정보를 모두 입력해 주세요!", Toast.LENGTH_LONG).show()
                } else {
                    regExerciseTypeViewModel.insertExerciseType(
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
                    Toast.makeText(this@RegExerciseTypeDetailActivity, "운동 등록을 완료하였습니다!", Toast.LENGTH_LONG).show()
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
                    dataBinding.tvRegister.setBackgroundColor(ContextCompat.getColor(context, R.color.grey))
                } else {
                    dataBinding.tvRegister.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
                }
            }
        })
    }
}