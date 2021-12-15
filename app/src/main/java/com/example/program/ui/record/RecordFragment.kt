package com.example.program.ui.record

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.program.R
import com.example.program.base.BaseFragment
import com.example.program.databinding.FragmentRecordBinding
import com.example.program.ui.record.sub.RecordDetailActivity

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