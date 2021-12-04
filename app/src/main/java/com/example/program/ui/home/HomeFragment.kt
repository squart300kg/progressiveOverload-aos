package com.example.program.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.program.R
import com.example.program.base.BaseFragment
import com.example.program.databinding.FragmentHomeBinding
import com.example.program.ui.dialog.RegisterDialog
import com.example.program.ui.home.sub.SplitSelectionActivity

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var dialog : RegisterDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            layoutAddProgram.layoutAddProgram.setOnClickListener {
                dialog = RegisterDialog.newInstance()
                dialog.show(requireActivity().supportFragmentManager, dialog.tag)
            }
        }
    }

}