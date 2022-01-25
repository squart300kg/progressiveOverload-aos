package com.progressive.overload.ui.record

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.progressive.overload.R
import com.progressive.overload.base.BaseFragment
import com.progressive.overload.databinding.FragmentRecordBinding
import com.progressive.overload.ui.home.TutorialMainActivity
import com.progressive.overload.ui.record.sub.RecordDetailActivity
import com.progressive.overload.util.GuideUtil
import com.securepreferences.SecurePreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecordFragment : BaseFragment<FragmentRecordBinding>(R.layout.fragment_record) {

    private val recordViewModel: RecordViewModel by viewModels()

    @Inject
    lateinit var securePreferences: SecurePreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            recordVm = recordViewModel

            // 튜토리얼 테스트
//            GuideUtil.saveRecordMainGuideShown(securePreferences, false)

            if (!GuideUtil.isRecordMainGuideShown(securePreferences)) {
                layoutRecordGuide.root.isVisible = true

                layoutRecordGuide.root.setOnClickListener {
                    layoutRecordGuide.root.isVisible = false
                    GuideUtil.saveRecordMainGuideShown(securePreferences, true)
                }
            }

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