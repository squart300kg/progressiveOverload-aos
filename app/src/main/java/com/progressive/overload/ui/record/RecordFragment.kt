package com.progressive.overload.ui.record

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.progressive.overload.R
import com.progressive.overload.base.BaseFragment
import com.progressive.overload.databinding.FragmentRecordBinding
import com.progressive.overload.ui.record.sub.RecordDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordFragment : BaseFragment<FragmentRecordBinding>(R.layout.fragment_record) {

    private val recordViewModel: RecordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            recordVm = recordViewModel

            rvPrograms.apply {
                setHasFixedSize(true)
                adapter = ProgramAdapter(requireActivity()) { programNo ->
                    Intent(requireActivity(), RecordDetailActivity::class.java).apply {
                        putExtra("programNo", programNo)
                        startActivity(this)
                    }
                }
            }
        }
        recordViewModel.getAllProgram()
    }

}