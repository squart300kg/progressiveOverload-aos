package com.example.program.ui.home

import android.annotation.SuppressLint
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
import com.example.program.ui.dialog.TitleDialog
import com.example.program.ui.home.sub.ExerciseTypeActivity
import com.example.program.ui.home.sub.MesoCycleSelectionActivity
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

                                    /**
                                     * 프로그램 복사를 위한 절차가 무엇이 있을까
                                     * 우선 첫 번쨰로 ProgramTable을 복사해야 할 것이다. 우선 이 작업은 완료했다
                                     * 그 후, 새로 복사된 레코드에서 'name'을 바꿔줘야 할 것이다.
                                     *
                                     * 우선적으로 제목만 바꾸는 작업을 허자.
                                     *
                                     *
                                     * 그 다음, ProgramTable에서 새로 만들어진 'no'를 참조하는 ExerciseTypeTable을 모조리 복해야 할 것이다
                                     *
                                     * 솔직히 트랜잭션 설정 안하고 쿼리들을 연속적으로 한다면 구현 자체느 가능할수잇다. 하지만.. 찝찝한 방식이라는 것이다
                                     */
                                    Log.i("duplicateProgram", "index 0")

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