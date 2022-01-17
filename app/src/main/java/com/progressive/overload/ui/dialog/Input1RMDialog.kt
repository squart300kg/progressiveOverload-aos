package com.progressive.overload.ui.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.progressive.overload.R
import com.progressive.overload.base.BaseCenterDialog
import com.progressive.overload.databinding.FragmentInput1rmDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import android.content.DialogInterface
import android.view.*


@AndroidEntryPoint
class Input1RMDialog(
    private val onClickCancel: () -> Unit,
    private val onClickOk: (String, String, String, String) -> Unit,
) : BaseCenterDialog<FragmentInput1rmDialogBinding>(R.layout.fragment_input_1rm_dialog) {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
//        dialog.setOnKeyListener { _, keyCode, keyEvent ->
//            // getAction to make sure this doesn't double fire
//            if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.action === KeyEvent.ACTION_UP) {
//                onClickCancel()
//                true // Capture onKey
//            } else false
//            // Don't capture
//        }
        return dialog
    }

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
            tvCancel.setOnClickListener {
                onClickCancel()
            }

            tvOk.setOnClickListener {
                onClickOk(
                    etSquart.text.toString(),
                    etDead.text.toString(),
                    etBench.text.toString(),
                    etMilp.text.toString()
                )
            }
        }
    }

    companion object {
        fun newInstance(
            onClickCancel: () -> Unit,
            onClickOk: (String, String, String, String) -> Unit,
        ) = Input1RMDialog(onClickCancel, onClickOk)

    }
}