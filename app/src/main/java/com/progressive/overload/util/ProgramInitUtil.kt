package com.progressive.overload.util

import android.util.Log
import com.progressive.overload.model.entity.ExerciseTypeTable

object ProgramInitUtil {

    fun initWeight(
        originExercises: List<ExerciseTypeTable>,
        squart1RM: String,
        dead1RM: String,
        bench1RM: String,
        milp1RM: String
    ): List<ExerciseTypeTable> {

        Log.i("ProgramInitUtil", "squart1RM : $squart1RM, dead1RM: $dead1RM, bench1RM: $bench1RM, milp1RM: $milp1RM\n exercises: $originExercises")


        return originExercises
    }
}