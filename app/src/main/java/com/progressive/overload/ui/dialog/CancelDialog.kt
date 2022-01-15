package com.progressive.overload.ui.dialog

import android.os.Bundle
import android.view.View
import com.progressive.overload.R
import com.progressive.overload.base.BaseBottomDialog
import com.progressive.overload.databinding.FragmentCancelDialogBinding
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