package com.example.program.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.example.program.R
import com.example.program.base.BaseFragment
import com.example.program.databinding.FragmentHomeBinding
import com.example.program.ui.dialog.UpdateDialog
import com.example.program.ui.home.sub.ExerciseTypeActivity
import com.example.program.ui.home.sub.MesoCycleSelectionActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var registerDialog: UpdateDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            homeVm = homeViewModel

            layoutAddProgram.layoutAddProgram.setOnClickListener {
                Intent(requireActivity(), MesoCycleSelectionActivity::class.java).apply {
                    startActivity(this)
                }
            }

            rvPrograms.apply {
                setHasFixedSize(true)
                adapter = MainProgramsAdapter(
                    { // 운동 종류 페이지 이동
                        Log.i("mainProgramAdpater", it.toString())
                        Intent(requireActivity(), ExerciseTypeActivity::class.java).apply {
                            putExtra("isIntentToExercise", true)
                            putExtra("mesoCycleSplitCount", it.mesoSplitCount)
                            putExtra("microCycleSplitCount", it.microCycleCount)
                            putExtra("programNo", it.no)
                            startActivity(this)
                        }
                    },
                    { programTable -> // 메뉴 아이콘 클릭
                        val builder = AlertDialog.Builder(ContextThemeWrapper(context,
                            R.style.AlertDialogCustom))
                        builder.setItems(R.array.home_sort_array
                        ) { dialog, pos ->
                            when (pos) {
                                0 -> { // 프로그램 이름 수정
                                    registerDialog =
                                        UpdateDialog.newInstance(
                                            programTable.no,
                                            programTable.name) {

                                            goMain()

                                            showToast("프로그램 등록을 완료하였습니다!")

                                        }
                                    registerDialog.show(
                                        requireActivity().supportFragmentManager,
                                        registerDialog.tag
                                    )
                                }
                                1 -> { // 프로그램 삭제
                                    val deleteDialog = AlertDialog.Builder(ContextThemeWrapper(
                                        context,
                                        R.style.AlertDialogCustom))
                                        .setCancelable(true)
                                        .setMessage("정말 삭제하시겠습니까?\n\n프로그램을 삭제하면 운동 기록도 모두 삭제됩니다.")
                                        .setPositiveButton("삭제") { dialog, _ ->
                                            homeViewModel.deleteProgram(programTable.no) {
                                                dialog?.dismiss()

                                                goMain()
                                            }
                                        }
                                        .setNegativeButton("취소") { _, _ -> }
                                        .create()
                                    deleteDialog.show()
                                }
                            }

                            dialog?.dismiss()
                        }
                        val dialog = builder.create()
                        dialog.show()
                    }
                )

            }
        }
    }

    override fun onStart() {
        super.onStart()

        homeViewModel.getAllProgram()

    }

}