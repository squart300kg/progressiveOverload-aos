package com.example.program.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.program.R
import com.example.program.base.BaseFragment
import com.example.program.databinding.FragmentHomeBinding
import com.example.program.ui.dialog.RegisterDialog
import com.example.program.ui.home.sub.SplitSelectionActivity

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel : HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            homeVm = homeViewModel
            layoutAddProgram.layoutAddProgram.setOnClickListener {
                Intent(requireActivity(), SplitSelectionActivity::class.java).apply {
                    startActivity(this)
                }
            }

            rvPrograms.apply {
                setHasFixedSize(true)
                adapter = MainProgramsAdapter() {
                    Log.i("mainProgramAdpater", it.toString())
                }

            }
        }
    }

    override fun onStart() {
        super.onStart()

        homeViewModel.getAllProgram()

    }

}