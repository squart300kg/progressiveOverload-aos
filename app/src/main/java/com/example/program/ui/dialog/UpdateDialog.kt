package com.example.program.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.program.R
import com.example.program.base.BaseCenterDialog
import com.example.program.databinding.FragmentRegisterDialogBinding
import com.example.program.ui.home.sub.RegExerciseTypeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateDialog(
    private val no: Long?,
    private val name: String? = "",
    private val success: () -> Unit = {},
) : BaseCenterDialog<FragmentRegisterDialogBinding>(R.layout.fragment_register_dialog) {

    private val viewModel: RegExerciseTypeViewModel by viewModels()

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
                    viewModel.updateProgramName(
                        no,
                        dataBinding.etInput.text.toString()
                    ) {
                        dismiss()
                        success()
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(
            no: Long?, name: String? = "", success: () -> Unit = {},
        ) =
            UpdateDialog(no, name, success)
    }
}