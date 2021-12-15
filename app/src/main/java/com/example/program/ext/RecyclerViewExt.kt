package com.example.program.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.program.model.entity.ProgramTable
import com.example.program.model.entity.RecordTable
import com.example.program.model.model.ExerciseTypeModel
import com.example.program.model.model.RecordExerciseModel
import com.example.program.model.model.RecordModel
import com.example.program.ui.home.MainProgramsAdapter
import com.example.program.ui.home.sub.ExerciseTypeAdapter
import com.example.program.ui.home.sub.RecordExerciseAdapter
import com.example.program.ui.record.ProgramAdapter
import com.example.program.ui.record.sub.RecordsAdapter

@BindingAdapter("overload:setItems")
fun <T> RecyclerView.setItems(items: List<T>?) {
    items?.let {
        when (adapter) {
            is ExerciseTypeAdapter -> {
                (adapter as ExerciseTypeAdapter).loadExercises(items as List<ExerciseTypeModel>)
            }
            is MainProgramsAdapter -> {
                (adapter as MainProgramsAdapter).loadPrograms(items as List<ProgramTable>)
            }
            is RecordExerciseAdapter -> {
                (adapter as RecordExerciseAdapter).loadRecord(items as List<RecordExerciseModel>)
            }
            is ProgramAdapter -> {
                (adapter as ProgramAdapter).loadPrograms(items as List<ProgramTable>)
            }
            is RecordsAdapter -> {
                (adapter as RecordsAdapter).loadRecords(items as List<RecordModel>)
            }
        }
    }
}