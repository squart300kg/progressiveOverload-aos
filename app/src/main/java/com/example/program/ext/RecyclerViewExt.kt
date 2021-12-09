package com.example.program.ext

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.ProgramTable
import com.example.program.model.model.RecordExerciseModel
import com.example.program.ui.home.MainProgramsAdapter
import com.example.program.ui.home.sub.ExerciseTypeAdapter
import com.example.program.ui.home.sub.RecordExerciseAdapter

@BindingAdapter("overload:setItems")
fun <T> RecyclerView.setItems(items: List<T>?) {
    items?.let {
        when (adapter) {
            is ExerciseTypeAdapter -> {
                (adapter as ExerciseTypeAdapter).loadExercises(items as List<ExerciseTypeTable>)
            }
            is MainProgramsAdapter -> {
                (adapter as MainProgramsAdapter).loadPrograms(items as List<ProgramTable>)
            }
            is RecordExerciseAdapter -> {
                (adapter as RecordExerciseAdapter).loadRecord(items as List<RecordExerciseModel>)
            }
        }
    }
}