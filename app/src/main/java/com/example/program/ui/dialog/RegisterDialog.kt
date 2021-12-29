package com.example.program.ui.dialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import com.example.program.R
import com.example.program.base.BaseCenterDialog
import com.example.program.databinding.FragmentRegisterDialogBinding
import com.example.program.ui.MainActivity
import com.example.program.ui.home.sub.RegExerciseTypeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterDialog(
    private val programNo: Long?,
    private val programName: String? = "",
) : BaseCenterDialog<FragmentRegisterDialogBinding>(R.layout.fragment_register_dialog) {

    private val viewModel: RegExerciseTypeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            etInput.setText(programName.toString())

            tvCancel.setOnClickListener {
                dismiss()
            }
            tvOk.setOnClickListener {
                if (etInput.text.isNullOrEmpty()) {
                    Toast.makeText(requireActivity(), "프로그램 이름을 입력해 주세요!", Toast.LENGTH_LONG).show()
                } else {
                    viewModel.updateProgramName(dataBinding.etInput.text.toString(), programNo) {
                        Intent(requireActivity(), MainActivity::class.java).apply {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(this)
                            Toast.makeText(requireActivity(), "프로그램 등록을 완료하였습니다!", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }

            }
        }
    }

    companion object {
        fun newInstance(programNo: Long?, programName: String? = "") =
            RegisterDialog(programNo, programName)
    }
}