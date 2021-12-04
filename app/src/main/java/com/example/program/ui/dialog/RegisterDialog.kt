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
import com.example.program.ui.home.sub.SplitSelectionActivity

class RegisterDialog : BaseCenterDialog<FragmentRegisterDialogBinding>(R.layout.fragment_register_dialog) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            tvCancel.setOnClickListener {
                dismiss()
            }
            tvOk.setOnClickListener {
                if (etInput.text.isNullOrEmpty()) {
                    Toast.makeText(requireActivity(), "프로그램 이름을 입력해 주세요!", Toast.LENGTH_LONG).show()
                }
                Intent(requireActivity(), SplitSelectionActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }

    companion object {
        fun newInstance() = RegisterDialog()
    }
}