package com.example.program.ui.dialog

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.example.program.R
import com.example.program.base.BaseCenterDialog
import com.example.program.databinding.FragmentDialogCompleteBinding

/**
 * Created by sangyoon on 2021/07/27
 */
class CompleteDialog(
    val name: String?,
    val onComplete: () -> Unit
): BaseCenterDialog<FragmentDialogCompleteBinding>(R.layout.fragment_dialog_complete) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            tvComplete.setOnClickListener {
                dialog?.dismiss()
                onComplete()
            }

            tvTitle.text = name + "완료!"
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onComplete()
    }

    companion object {
        fun newInstance(
            name: String?,
            onComplete: () -> Unit
        ) = CompleteDialog(name, onComplete)
    }

}