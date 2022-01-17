package com.progressive.overload.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.View
import androidx.fragment.app.viewModels
import com.progressive.overload.R
import com.progressive.overload.base.BaseFragment
import com.progressive.overload.databinding.FragmentHomeBinding
import com.progressive.overload.ui.dialog.TitleDialog
import com.progressive.overload.ui.home.sub.ExerciseTypeActivity
import com.progressive.overload.ui.home.sub.MesoCycleSelectionActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var registerDialog: TitleDialog

    private lateinit var mainProgramsAdapter: MainProgramsAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            homeVm = homeViewModel

            // 운동 종류 등록
            layoutAddProgram.layoutAddProgram.setOnClickListener {
                Intent(requireActivity(), MesoCycleSelectionActivity::class.java).apply {
                    startActivity(this)
                    requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }

            rvPrograms.apply {
                setHasFixedSize(true)
                mainProgramsAdapter = MainProgramsAdapter(
                    { // 운동 종류 페이지 이동
                        Log.i("mainProgramAdpater", it.toString())
                        Intent(requireActivity(), ExerciseTypeActivity::class.java).apply {
                            putExtra("isIntentToExercise", true)
                            putExtra("isDummyDataInit", it.isDummyDataInit)
                            putExtra("isDummy", it.isDummy)
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
                                0 -> { // 프로그램 복사

                                    registerDialog =
                                        TitleDialog.newInstance(
                                            TitleDialog.INTENT_TO_DUPLICATE,
                                            programTable.no,
                                            programTable.name) {
                                            homeViewModel.getAllProgram()
                                        }
                                    registerDialog.show(
                                        requireActivity().supportFragmentManager,
                                        registerDialog.tag
                                    )
                                }
                                1 -> { // 프로그램 이름 수정
                                    registerDialog =
                                        TitleDialog.newInstance(
                                            TitleDialog.INTENT_TO_UPDATE,
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
                                2 -> { // 프로그램 삭제
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
                adapter = mainProgramsAdapter
            }
        }
    }


    override fun onStart() {
        super.onStart()

        homeViewModel.getAllProgram()

    }

}