package com.example.program.ui.home.sub

import android.util.Log
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.constant.Split
import com.example.program.databinding.ItemExerciseTypeBinding
import com.example.program.model.entity.ExerciseTypeTable

/**
 * Created by sangyoon on 2021/07/27
 */
class RegExerciseTypeAdapter(
    val onClick : (item : ExerciseTypeTable) -> Unit
): RecyclerView.Adapter<RegExerciseTypeAdapter.ExercisesViewHolder>() {

    private val items: MutableList<ExerciseTypeTable> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExercisesViewHolder {
        return ExercisesViewHolder(
            BR.exerciseItem,
            parent,
            R.layout.item_exercise_type
        )
    }

    override fun onBindViewHolder(holder: ExercisesViewHolder, position: Int) {
        holder.bindItem(items[position])

        holder.itemView.setOnClickListener {
            onClick(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    fun loadExercises(list: List<ExerciseTypeTable>) {
        Log.i("loadExercises", list.toString())
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class ExercisesViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<ExerciseTypeTable, ItemExerciseTypeBinding>(itemId, parent, layoutRes) {

    }

}


