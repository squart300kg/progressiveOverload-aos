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
import com.example.program.base.BaseBottomDialog
import com.example.program.databinding.FragmentCancelDialogBinding
import com.example.program.databinding.FragmentRegisterDialogBinding
import com.example.program.ui.home.sub.SplitSelectionActivity

class CancelDialog(
    private val clickForNoSave: () -> Unit,
    private val clickForSave: () -> Unit
) : BaseBottomDialog<FragmentCancelDialogBinding>(R.layout.fragment_cancel_dialog) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            tvCancel.setOnClickListener {
                dismiss()
            }
            tvNoSave.setOnClickListener {
                dismiss()
                clickForNoSave()
            }
            tvSave.setOnClickListener {
                dismiss()
                clickForSave()
            }
        }
    }

    companion object {
        fun newInstance(
            clickForNoSave: () -> Unit,
            clickForSave: () -> Unit
        ) = CancelDialog(
            clickForNoSave,
            clickForSave
        )
    }
}