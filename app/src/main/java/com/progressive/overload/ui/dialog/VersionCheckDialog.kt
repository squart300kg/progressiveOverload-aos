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
import com.progressive.overload.databinding.FragmentVersionCheckDialogBinding
import com.progressive.overload.ui.home.HomeViewModel
import com.progressive.overload.ui.home.sub.RegExerciseTypeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VersionCheckDialog(
    private val intent: Int,
    private val success: () -> Unit = {},
) : BaseCenterDialog<FragmentVersionCheckDialogBinding>(R.layout.fragment_version_check_dialog) {

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

    }

    companion object {
        fun newInstance(
            intent: Int, success: () -> Unit = {},
        ) = VersionCheckDialog(intent, success)

    }
}