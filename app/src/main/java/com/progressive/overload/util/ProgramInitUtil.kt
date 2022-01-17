package com.progressive.overload.util

import android.util.Log
import com.progressive.overload.model.entity.ExerciseTypeTable

object ProgramInitUtil {

    fun initWeight(
        originExercises: List<ExerciseTypeTable>,
        squart1RM: Float,
        dead1RM: Float,
        bench1RM: Float,
        milp1RM: Float
    ): List<ExerciseTypeTable> {

        Log.i("ProgramInitUtil", "squart1RM : $squart1RM, dead1RM: $dead1RM, bench1RM: $bench1RM, milp1RM: $milp1RM\n exercises: $originExercises")

        originExercises.forEachIndexed { index, it ->
            if (it.mesoCycleSplitIndex == 0) {
                if (it.microCycleSplitIndex == 0) {
                    if (it.name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.55f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:인클라인")) {
                        it.weight = getPercentageWeight(0.9f * 0.5f, bench1RM)
                    }
                    if (it.name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.55f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:바벨로우")) {
                        it.weight = getPercentageWeight(0.55f, bench1RM) - 10f
                    }
                }
                if (it.microCycleSplitIndex == 1) {
                    if (it.name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.55f, squart1RM)
                    }
                    if (it.name.startsWith("보조운동A:프론트스쿼트")) {
                        it.weight = getPercentageWeight(0.7f * 0.5f, squart1RM)
                    }
                    if (it.name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.55f, milp1RM)
                    }
                    if (it.name.startsWith("보조운동A:비하인드")) {
                        it.weight = getPercentageWeight(0.85f * 0.5f, milp1RM)
                    }
                }
                if (it.microCycleSplitIndex == 2) {
                    if (it.name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.55f, dead1RM)
                    }
                    if (it.name.startsWith("보조운동A:데드리프트2")) {
                        it.weight = getPercentageWeight(0.9f * 0.5f, dead1RM)
                    }
                    if (it.name.startsWith("삼두메인:클로즈")) {
                        it.weight = getPercentageWeight(0.55f, bench1RM) - 5f
                    }
                }
                if (it.microCycleSplitIndex == 3) {
                    if (it.name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.6f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:인클라인")) {
                        it.weight = getPercentageWeight(0.9f * 0.55f, bench1RM)
                    }
                    if (it.name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.6f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:바벨로우")) {
                        it.weight = getPercentageWeight(0.6f, bench1RM) - 10f
                    }
                }
                if (it.microCycleSplitIndex == 4) {
                    if (it.name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.6f, squart1RM)
                    }
                    if (it.name.startsWith("보조운동A:프론트스쿼트")) {
                        it.weight = getPercentageWeight(0.7f * 0.55f, squart1RM)
                    }
                    if (it.name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.6f, milp1RM)
                    }
                    if (it.name.startsWith("보조운동A:비하인드")) {
                        it.weight = getPercentageWeight(0.85f * 0.55f, milp1RM)
                    }
                }
                if (it.microCycleSplitIndex == 5) {
                    if (it.name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.6f, dead1RM)
                    }
                    if (it.name.startsWith("보조운동A:데드리프트2")) {
                        it.weight = getPercentageWeight(0.9f * 0.55f, dead1RM)
                    }
                    if (it.name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.6f, bench1RM) - 5f
                    }
                }
            }
            if (it.mesoCycleSplitIndex == 1) {
                if (it.microCycleSplitIndex == 0) {
                    if (it.name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.65f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:인클라인")) {
                        it.weight = getPercentageWeight(0.9f * 0.6f, bench1RM)
                    }
                    if (it.name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.65f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:바벨로우")) {
                        it.weight = getPercentageWeight(0.65f, bench1RM) - 10f
                    }
                }
                if (it.microCycleSplitIndex == 1) {
                    if (it.name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.65f, squart1RM)
                    }
                    if (it.name.startsWith("보조운동A:프론트스쿼트")) {
                        it.weight = getPercentageWeight(0.7f * 0.6f, squart1RM)
                    }
                    if (it.name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.65f, milp1RM)
                    }
                    if (it.name.startsWith("보조운동A:비하인드")) {
                        it.weight = getPercentageWeight(0.85f * 0.6f, milp1RM)
                    }
                }
                if (it.microCycleSplitIndex == 2) {
                    if (it.name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.65f, dead1RM)
                    }
                    if (it.name.startsWith("보조운동A:데드리프트2")) {
                        it.weight = getPercentageWeight(0.9f * 0.6f, dead1RM)
                    }
                    if (it.name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.65f, bench1RM) - 5f
                    }
                }
                if (it.microCycleSplitIndex == 3) {
                    if (it.name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.7f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:인클라인")) {
                        it.weight = getPercentageWeight(0.9f * 0.65f, bench1RM)
                    }
                    if (it.name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.7f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:바벨로우")) {
                        it.weight = getPercentageWeight(0.7f, bench1RM) - 10f
                    }
                }
                if (it.microCycleSplitIndex == 4) {
                    if (it.name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.7f, squart1RM)
                    }
                    if (it.name.startsWith("보조운동A:프론트스쿼트")) {
                        it.weight = getPercentageWeight(0.7f * 0.65f, squart1RM)
                    }
                    if (it.name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.7f, milp1RM)
                    }
                    if (it.name.startsWith("보조운동A:비하인드")) {
                        it.weight = getPercentageWeight(0.85f * 0.65f, milp1RM)
                    }
                }
                if (it.microCycleSplitIndex == 5) {
                    if (it.name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.7f, dead1RM)
                    }
                    if (it.name.startsWith("보조운동A:데드리프트2")) {
                        it.weight = getPercentageWeight(0.9f * 0.65f, dead1RM)
                    }
                    if (it.name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.7f, bench1RM) - 5f
                    }
                }
            }
            if (it.mesoCycleSplitIndex == 2) {
                if (it.microCycleSplitIndex == 0) {
                    if (originExercises[index-1].name.startsWith("메인운동:벤치프레스") &&
                        originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.75f, bench1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.75f, bench1RM)
                    }

                    if (it.name.startsWith("보조운동A:인클라인")) {
                        it.weight = getPercentageWeight(0.9f * 0.675f, bench1RM)
                    }

                    if (originExercises[index-1].name.startsWith("메인운동:펜들레이로우") &&
                        originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.75f, bench1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.75f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:바벨로우")) {
                        it.weight = getPercentageWeight(0.75f, bench1RM) - 10f
                    }
                }
                if (it.microCycleSplitIndex == 1) {
                    if (originExercises[index-1].name.startsWith("메인운동:스쿼트") &&
                        originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.75f, squart1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.75f, squart1RM)
                    }

                    if (it.name.startsWith("보조운동A:프론트스쿼트")) {
                        it.weight = getPercentageWeight(0.7f * 0.7f, squart1RM)
                    }

                    if (originExercises[index-1].name.startsWith("메인운동:OHP") &&
                        originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.75f, milp1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.75f, milp1RM)
                    }
                    if (it.name.startsWith("보조운동A:비하인드")) {
                        it.weight = getPercentageWeight(0.85f * 0.7f, milp1RM)
                    }
                }
                if (it.microCycleSplitIndex == 2) {
                    if (originExercises[index-1].name.startsWith("메인운동:데드리프트") &&
                        originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.75f, dead1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.75f, dead1RM)
                    }

                    if (originExercises[index-1].name.startsWith("보조운동A:데드리프트2") &&
                        originExercises[index].name.startsWith("보조운동A:데드리프트2")) {
                        it.weight = getPercentageWeight(0.9f * 0.6f, dead1RM)
                    } else if (originExercises[index].name.startsWith("보조운동A:데드리프트2")) {
                        it.weight = getPercentageWeight(0.9f * 0.7f, dead1RM)
                    }

                    if (originExercises[index-1].name.startsWith("삼두메인:클로즈벤치") &&
                        originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.75f, bench1RM)) - 5f
                    } else if (originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.75f, bench1RM) - 5f
                    }
                }
                if (it.microCycleSplitIndex == 3) {
                    if (originExercises[index-1].name.startsWith("메인운동:벤치프레스") &&
                        originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.775f, bench1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.775f, bench1RM)
                    }

                    if (it.name.startsWith("보조운동A:인클라인")) {
                        it.weight = getPercentageWeight(0.9f * 0.7f, bench1RM)
                    }
                    if (originExercises[index-1].name.startsWith("메인운동:펜들레이로우") &&
                        originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.775f, bench1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.775f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:바벨로우")) {
                        it.weight = getPercentageWeight(0.775f, bench1RM) - 10f
                    }
                }
                if (it.microCycleSplitIndex == 4) {
                    if (originExercises[index-1].name.startsWith("메인운동:스쿼트") &&
                        originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.775f, squart1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.775f, squart1RM)
                    }

                    if (it.name.startsWith("보조운동A:프론트스쿼트")) {
                        it.weight = getPercentageWeight(0.7f * 0.75f, squart1RM)
                    }

                    if (originExercises[index-1].name.startsWith("메인운동:OHP") &&
                        originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.775f, milp1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.775f, milp1RM)
                    }
                    if (it.name.startsWith("보조운동A:비하인드")) {
                        it.weight = getPercentageWeight(0.85f * 0.75f, milp1RM)
                    }
                }
                if (it.microCycleSplitIndex == 5) {
                    if (originExercises[index-1].name.startsWith("메인운동:데드리프트") &&
                        originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.775f, dead1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.775f, dead1RM)
                    }

                    if (originExercises[index-1].name.startsWith("보조운동A:데드리프트2") &&
                        originExercises[index].name.startsWith("보조운동A:데드리프트2")) {
                        it.weight = getPercentageWeight(0.9f * 0.65f, dead1RM)
                    } else if (originExercises[index].name.startsWith("보조운동A:데드리프트2")) {
                        it.weight = getPercentageWeight(0.9f * 0.75f, dead1RM)
                    }

                    if (originExercises[index-1].name.startsWith("삼두메인:클로즈벤치") &&
                        originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.775f, bench1RM)) - 5f
                    } else if (originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.775f, bench1RM) - 5f
                    }
                }
            }
            if (it.mesoCycleSplitIndex == 3) {
                if (it.microCycleSplitIndex == 0) {
                    if (originExercises[index-1].name.startsWith("메인운동:벤치프레스") &&
                        originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.8f, bench1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.8f, bench1RM)
                    }

                    if (it.name.startsWith("보조운동A:인클라인")) {
                        it.weight = getPercentageWeight(0.9f * 0.725f, bench1RM)
                    }
                    if (originExercises[index-1].name.startsWith("메인운동:펜들레이로우") &&
                        originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.8f, bench1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.8f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:바벨로우")) {
                        it.weight = getPercentageWeight(0.8f, bench1RM) - 10f
                    }
                }
                if (it.microCycleSplitIndex == 1) {
                    if (originExercises[index-1].name.startsWith("메인운동:스쿼트") &&
                        originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.8f, squart1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.8f, squart1RM)
                    }

                    if (it.name.startsWith("보조운동A:프론트스쿼트")) {
                        it.weight = getPercentageWeight(0.7f * 0.775f, squart1RM)
                    }

                    if (originExercises[index-1].name.startsWith("메인운동:OHP") &&
                        originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.8f, milp1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.8f, milp1RM)
                    }
                    if (it.name.startsWith("보조운동A:비하인드")) {
                        it.weight = getPercentageWeight(0.85f * 0.775f, milp1RM)
                    }
                }
                if (it.microCycleSplitIndex == 2) {
                    if (originExercises[index-1].name.startsWith("메인운동:데드리프트") &&
                        originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.8f, dead1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.8f, dead1RM)
                    }

                    if (originExercises[index].name.startsWith("보조운동A:데드리프트2")) {
                        it.weight = getPercentageWeight(0.9f * 0.775f, dead1RM)
                    }

                    if (originExercises[index-1].name.startsWith("삼두메인:클로즈벤치") &&
                        originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.8f, bench1RM)) - 5f
                    } else if (originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.8f, bench1RM) - 5f
                    }
                }
                if (it.microCycleSplitIndex == 3) {
                    if (originExercises[index-1].name.startsWith("메인운동:벤치프레스") &&
                        originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.825f, bench1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.825f, bench1RM)
                    }

                    if (it.name.startsWith("보조운동A:인클라인")) {
                        it.weight = getPercentageWeight(0.9f * 0.75f, bench1RM)
                    }

                    if (originExercises[index-1].name.startsWith("메인운동:펜들레이로우") &&
                        originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.8f, bench1RM) + 2.5f)
                    } else if (originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.8f, bench1RM) + 2.5f
                    }
                    if (it.name.startsWith("보조운동A:바벨로우")) {
                        it.weight = getPercentageWeight(0.825f, bench1RM) - 10f
                    }
                }
                if (it.microCycleSplitIndex == 4) {
                    if (originExercises[index-1].name.startsWith("메인운동:스쿼트") &&
                        originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.825f, squart1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.825f, squart1RM)
                    }

                    if (it.name.startsWith("보조운동A:프론트스쿼트")) {
                        it.weight = getPercentageWeight(0.7f * 0.8f, squart1RM)
                    }

                    if (originExercises[index-1].name.startsWith("메인운동:OHP") &&
                        originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.825f, milp1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.825f, milp1RM)
                    }
                    if (it.name.startsWith("보조운동A:비하인드")) {
                        it.weight = getPercentageWeight(0.85f * 0.8f, milp1RM)
                    }
                }
                if (it.microCycleSplitIndex == 5) {
                    if (originExercises[index-1].name.startsWith("메인운동:데드리프트") &&
                        originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.825f, dead1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.825f, dead1RM)
                    }

                    if (originExercises[index].name.startsWith("보조운동A:데드리프트2")) {
                        it.weight = getPercentageWeight(0.9f * 0.8f, dead1RM)
                    }

                    if (originExercises[index-1].name.startsWith("삼두메인:클로즈벤치") &&
                        originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.825f, bench1RM)) - 5f
                    } else if (originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.825f, bench1RM) - 5f
                    }
                }
            }
            if (it.mesoCycleSplitIndex == 4) {
                if (it.microCycleSplitIndex == 0) {
                    if (originExercises[index-1].name.startsWith("메인운동:벤치프레스") &&
                        originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.85f, bench1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.85f, bench1RM)
                    }

                    if (it.name.startsWith("보조운동A:인클라인")) {
                        it.weight = getPercentageWeight(0.9f * 0.775f, bench1RM)
                    }

                    if (originExercises[index-1].name.startsWith("메인운동:펜들레이로우") &&
                        originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.85f, bench1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.85f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:바벨로우")) {
                        it.weight = getPercentageWeight(0.85f, bench1RM) - 10f
                    }
                }
                if (it.microCycleSplitIndex == 1) {
                    if (originExercises[index-1].name.startsWith("메인운동:스쿼트") &&
                        originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.85f, squart1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.85f, squart1RM)
                    }

                    if (it.name.startsWith("보조운동A:프론트스쿼트")) {
                        it.weight = getPercentageWeight(0.7f * 0.825f, squart1RM)
                    }

                    if (originExercises[index-1].name.startsWith("메인운동:OHP") &&
                        originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.85f, milp1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.85f, milp1RM)
                    }
                    if (it.name.startsWith("보조운동A:비하인드")) {
                        it.weight = getPercentageWeight(0.85f * 0.825f, milp1RM)
                    }
                }
                if (it.microCycleSplitIndex == 2) {
                    if (originExercises[index-1].name.startsWith("메인운동:데드리프트") &&
                        originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.85f, dead1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.85f, dead1RM)
                    }

                    if (originExercises[index-1].name.startsWith("삼두메인:클로즈벤치") &&
                        originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.85f, bench1RM)) - 5f
                    } else if (originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.85f, bench1RM) - 5f
                    }
                }
                if (it.microCycleSplitIndex == 3) {
                    if (originExercises[index-1].name.startsWith("메인운동:벤치프레스") &&
                        originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.875f, bench1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.875f, bench1RM)
                    }

                    if (it.name.startsWith("보조운동A:인클라인")) {
                        it.weight = getPercentageWeight(0.9f * 0.8f, bench1RM)
                    }

                    if (originExercises[index-1].name.startsWith("메인운동:펜들레이로우") &&
                        originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.875f, bench1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.875f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:바벨로우")) {
                        it.weight = getPercentageWeight(0.875f, bench1RM) - 10f
                    }
                }
                if (it.microCycleSplitIndex == 4) {
                    if (originExercises[index-1].name.startsWith("메인운동:스쿼트") &&
                        originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.875f, squart1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.875f, squart1RM)
                    }

                    if (it.name.startsWith("보조운동A:프론트스쿼트")) {
                        it.weight = getPercentageWeight(0.7f * 0.85f, squart1RM)
                    }

                    if (originExercises[index-1].name.startsWith("메인운동:OHP") &&
                        originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.875f, milp1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.875f, milp1RM)
                    }
                    if (it.name.startsWith("보조운동A:비하인드")) {
                        it.weight = getPercentageWeight(0.85f * 0.85f, milp1RM)
                    }
                }
                if (it.microCycleSplitIndex == 5) {
                    if (originExercises[index-1].name.startsWith("메인운동:데드리프트") &&
                        originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.875f, dead1RM))
                    } else if (originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.875f, dead1RM)
                    }

                    if (originExercises[index-1].name.startsWith("삼두메인:클로즈벤치") &&
                        originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.9f, getPercentageWeight(0.875f, bench1RM)) - 5f
                    } else if (originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.875f, bench1RM) - 5f
                    }
                }
            }
            if (it.mesoCycleSplitIndex == 5) {
                if (it.microCycleSplitIndex == 0) {
                    if (originExercises[index-1].name.startsWith("메인운동:벤치프레스") &&
                        originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.925f, bench1RM)
                    } else if (originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.9f, bench1RM)
                    }

                    if (it.name.startsWith("보조운동A:인클라인")) {
                        it.weight = getPercentageWeight(0.9f * 0.825f, bench1RM)
                    }

                    if (originExercises[index-1].name.startsWith("메인운동:펜들레이로우") &&
                        originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.925f, bench1RM)
                    } else if (originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.9f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:바벨로우")) {
                        it.weight = getPercentageWeight(0.9f, bench1RM) - 10f
                    }
                }
                if (it.microCycleSplitIndex == 1) {
                    if (originExercises[index-1].name.startsWith("메인운동:스쿼트") &&
                        originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.925f, squart1RM)
                    } else if (originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.9f, squart1RM)
                    }

                    if (it.name.startsWith("보조운동A:프론트스쿼트")) {
                        it.weight = getPercentageWeight(0.7f * 0.875f, squart1RM)
                    }

                    if (originExercises[index-1].name.startsWith("메인운동:OHP") &&
                        originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.925f, milp1RM)
                    } else if (originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.9f, milp1RM)
                    }

                    if (it.name.startsWith("보조운동A:비하인드")) {
                        it.weight = getPercentageWeight(0.85f * 0.875f, milp1RM)
                    }
                }
                if (it.microCycleSplitIndex == 2) {
                    if (originExercises[index-1].name.startsWith("메인운동:데드리프트") &&
                        originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.925f, dead1RM)
                    } else if (originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.9f, dead1RM)
                    }

                    if (originExercises[index-1].name.startsWith("삼두메인:클로즈벤치") &&
                        originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.925f, bench1RM) - 5f
                    } else if (originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.9f, bench1RM) - 5f
                    }
                }
                if (it.microCycleSplitIndex == 3) {
                    if (originExercises[index-1].name.startsWith("메인운동:벤치프레스") &&
                        originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.95f, bench1RM)
                    } else if (originExercises[index].name.startsWith("메인운동:벤치프레스")) {
                        it.weight = getPercentageWeight(0.925f, bench1RM)
                    }

                    if (it.name.startsWith("보조운동A:인클라인")) {
                        it.weight = getPercentageWeight(0.9f * 0.85f, bench1RM)
                    }

                    if (originExercises[index-1].name.startsWith("메인운동:펜들레이로우") &&
                        originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.95f, bench1RM)
                    } else if (originExercises[index].name.startsWith("메인운동:펜들레이로우")) {
                        it.weight = getPercentageWeight(0.925f, bench1RM)
                    }
                    if (it.name.startsWith("보조운동A:바벨로우")) {
                        it.weight = getPercentageWeight(0.95f, bench1RM) - 10f
                    }
                }
                if (it.microCycleSplitIndex == 4) {
                    if (originExercises[index-1].name.startsWith("메인운동:스쿼트") &&
                        originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.95f, squart1RM)
                    } else if (originExercises[index].name.startsWith("메인운동:스쿼트")) {
                        it.weight = getPercentageWeight(0.925f, squart1RM)
                    }

                    if (it.name.startsWith("보조운동A:프론트스쿼트")) {
                        it.weight = getPercentageWeight(0.7f * 0.9f, squart1RM)
                    }

                    if (originExercises[index-1].name.startsWith("메인운동:OHP") &&
                        originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.95f, milp1RM)
                    } else if (originExercises[index].name.startsWith("메인운동:OHP")) {
                        it.weight = getPercentageWeight(0.925f, milp1RM)
                    }

                    if (it.name.startsWith("보조운동A:비하인드")) {
                        it.weight = getPercentageWeight(0.85f * 0.9f, milp1RM)
                    }
                }
                if (it.microCycleSplitIndex == 5) {
                    if (originExercises[index-1].name.startsWith("메인운동:데드리프트") &&
                        originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.95f, dead1RM)
                    } else if (originExercises[index].name.startsWith("메인운동:데드리프트")) {
                        it.weight = getPercentageWeight(0.925f, dead1RM)
                    }

                    if (originExercises[index-1].name.startsWith("삼두메인:클로즈벤치") &&
                        originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.95f, bench1RM) - 5f
                    } else if (originExercises[index].name.startsWith("삼두메인:클로즈벤치")) {
                        it.weight = getPercentageWeight(0.925f, bench1RM) - 5f
                    }
                }
            }
        }

        return originExercises
    }

    private fun getPercentageWeight(percentage: Float, weightOf1RM: Float): Float {
        val percentageWeight = weightOf1RM * percentage
        val remainder = percentageWeight % 2.5f
        return percentageWeight - remainder
    }
}