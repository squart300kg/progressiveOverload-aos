package com.example.program.ui.dialog

import android.os.Bundle
import android.view.View
import com.example.program.R
import com.example.program.base.BaseBottomDialog
import com.example.program.databinding.FragmentCancelDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    override fun getTheme(): Int {
        return R.style.FullScreenDialog
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