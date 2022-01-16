package com.progressive.overload.util

import android.util.Log
import com.progressive.overload.model.entity.ExerciseTypeTable
import java.lang.Math.floor

object ProgramInitUtil {

    fun initWeight(
        originExercises: List<ExerciseTypeTable>,
        squart1RM: Float,
        dead1RM: Float,
        bench1RM: Float,
        milp1RM: Float
    ): List<ExerciseTypeTable> {

        Log.i("ProgramInitUtil", "squart1RM : $squart1RM, dead1RM: $dead1RM, bench1RM: $bench1RM, milp1RM: $milp1RM\n exercises: $originExercises")

        originExercises.forEach {
            if (it.mesoCycleSplitIndex == 0) {
                if (it.microCycleSplitIndex == 0) {
                    if (it.name.startsWith("메인운동:벤치프레스")) {
                        val weight = (bench1RM * 0.55f)
                        val remainder = weight % 2.5f
                        it.weight = weight - remainder
                    }
                    if (it.name.startsWith("보조운동A:인클라인")) {
                        val weight = (bench1RM * 0.9f * 0.5f)
                        val remainder = weight % 2.5f
                        it.weight = weight - remainder
                    }
                }
                if (it.microCycleSplitIndex == 1) { }
                if (it.microCycleSplitIndex == 2) { }
                if (it.microCycleSplitIndex == 3) { }
                if (it.microCycleSplitIndex == 4) { }
                if (it.microCycleSplitIndex == 5) { }
            }
            if (it.mesoCycleSplitIndex == 1) {
                if (it.microCycleSplitIndex == 0) { }
                if (it.microCycleSplitIndex == 1) { }
                if (it.microCycleSplitIndex == 2) { }
                if (it.microCycleSplitIndex == 3) { }
                if (it.microCycleSplitIndex == 4) { }
                if (it.microCycleSplitIndex == 5) { }
            }
            if (it.mesoCycleSplitIndex == 2) {
                if (it.microCycleSplitIndex == 0) { }
                if (it.microCycleSplitIndex == 1) { }
                if (it.microCycleSplitIndex == 2) { }
                if (it.microCycleSplitIndex == 3) { }
                if (it.microCycleSplitIndex == 4) { }
                if (it.microCycleSplitIndex == 5) { }
            }
            if (it.mesoCycleSplitIndex == 3) {
                if (it.microCycleSplitIndex == 0) { }
                if (it.microCycleSplitIndex == 1) { }
                if (it.microCycleSplitIndex == 2) { }
                if (it.microCycleSplitIndex == 3) { }
                if (it.microCycleSplitIndex == 4) { }
                if (it.microCycleSplitIndex == 5) { }
            }
            if (it.mesoCycleSplitIndex == 4) {
                if (it.microCycleSplitIndex == 0) { }
                if (it.microCycleSplitIndex == 1) { }
                if (it.microCycleSplitIndex == 2) { }
                if (it.microCycleSplitIndex == 3) { }
                if (it.microCycleSplitIndex == 4) { }
                if (it.microCycleSplitIndex == 5) { }
            }
            if (it.mesoCycleSplitIndex == 5) {
                if (it.microCycleSplitIndex == 0) { }
                if (it.microCycleSplitIndex == 1) { }
                if (it.microCycleSplitIndex == 2) { }
                if (it.microCycleSplitIndex == 3) { }
                if (it.microCycleSplitIndex == 4) { }
                if (it.microCycleSplitIndex == 5) { }
            }
        }

        return originExercises
    }
}