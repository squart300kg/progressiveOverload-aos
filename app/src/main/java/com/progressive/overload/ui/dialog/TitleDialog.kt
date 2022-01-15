package com.progressive.overload.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.progressive.overload.R
import com.progressive.overload.base.BaseCenterDialog
import com.progressive.overload.databinding.FragmentRegisterDialogBinding
import com.progressive.overload.ui.home.HomeViewModel
import com.progressive.overload.ui.home.sub.RegExerciseTypeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TitleDialog(
    private val intent: Int,
    private val no: Long,
    private val name: String = "",
    private val success: () -> Unit = {},
) : BaseCenterDialog<FragmentRegisterDialogBinding>(R.layout.fragment_register_dialog) {

    private val regExerciseTypeViewModel: RegExerciseTypeViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            etInput.setText(name)

            tvCancel.setOnClickListener {
                dismiss()
            }

            tvOk.setOnClickListener {
                if (etInput.text.isNullOrEmpty()) {
                    Toast.makeText(requireActivity(), "이름을 입력해 주세요!", Toast.LENGTH_LONG).show()
                } else {

                    when (intent) {
                        INTENT_TO_UPDATE -> { // 프로그램 제목 변경
                            regExerciseTypeViewModel.updateProgramName(
                                no,
                                dataBinding.etInput.text.toString()
                            ) {
                                dismiss()
                                success()
                            }
                        }
                        INTENT_TO_DUPLICATE -> { // 프로그램 복사

                            // 기존 프로그램 복사 후, 기존 프로그램 안에 있는 운동 종목 모두 복사
                            homeViewModel.duplicateProgram(
                                no,
                                dataBinding.etInput.text.toString()) {
                                dismiss()
                                success()
                            }
                        }
                    }

                }
            }
        }
    }

    companion object {
        fun newInstance(
            intent: Int, no: Long, name: String = "", success: () -> Unit = {},
        ) = TitleDialog(intent, no, name, success)

        const val INTENT_TO_UPDATE = 0
        const val INTENT_TO_DUPLICATE = 1
    }
}