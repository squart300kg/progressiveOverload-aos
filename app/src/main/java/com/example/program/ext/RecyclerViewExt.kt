package com.example.program.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.ui.home.sub.RegExerciseTypeAdapter

@BindingAdapter("overload:setItems")
fun <T> RecyclerView.setItems(items: List<T>?) {
    items?.let {
        when(adapter) {
            is RegExerciseTypeAdapter -> {
                (adapter as RegExerciseTypeAdapter).loadExercises(items as List<ExerciseTypeTable>)
            }
        }
    }
}