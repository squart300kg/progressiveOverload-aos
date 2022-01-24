package com.progressive.overload.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.progressive.overload.model.entity.ProgramTable
import com.progressive.overload.model.model.ExerciseTypeModel
import com.progressive.overload.model.model.HomeProgramModel
import com.progressive.overload.model.model.RecordExerciseModel
import com.progressive.overload.model.model.RecordModel
import com.progressive.overload.ui.home.MainProgramsAdapter
import com.progressive.overload.ui.home.sub.ExerciseTypeAdapter
import com.progressive.overload.ui.home.sub.RecordExerciseAdapter
import com.progressive.overload.ui.record.ProgramAdapter
import com.progressive.overload.ui.record.sub.OneDayRecordAdapter
import com.progressive.overload.ui.record.sub.RecordsAdapter

@BindingAdapter("overload:setItems")
fun <T> RecyclerView.setItems(items: List<T>?) {
    items?.let {
        when (adapter) {
            is ExerciseTypeAdapter -> {
                (adapter as ExerciseTypeAdapter).loadExercises(items as List<ExerciseTypeModel>)
            }
            is MainProgramsAdapter -> {
                (adapter as MainProgramsAdapter).loadPrograms(items as List<HomeProgramModel>)
            }
            is RecordExerciseAdapter -> {
                (adapter as RecordExerciseAdapter).loadRecord(items as List<RecordExerciseModel>)
            }
            is ProgramAdapter -> {
                (adapter as ProgramAdapter).loadPrograms(items as List<HomeProgramModel>)
            }
            is RecordsAdapter -> {
                (adapter as RecordsAdapter).loadRecords(items as List<RecordModel>)
            }
            is OneDayRecordAdapter -> {
                (adapter as OneDayRecordAdapter).loadOneDayRecordName(items as List<String>)
            }
        }
    }
}