package com.example.program.ui.home.sub

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.databinding.ItemExerciseTypeBinding
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.model.ExerciseTypeModel

/**
 * Created by sangyoon on 2021/07/27
 */
class ExerciseTypeAdapter(
    val context: Context,
    val onClickForUpdate: (item: ExerciseTypeModel) -> Unit,
    val onClickForRecord: (item: ExerciseTypeModel) -> Unit,
) : RecyclerView.Adapter<ExerciseTypeAdapter.ExercisesViewHolder>() {

    private val items: MutableList<ExerciseTypeModel> = mutableListOf()

    private var isExerciseStarted = false

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ExercisesViewHolder {
        return ExercisesViewHolder(
            BR.exerciseItem,
            parent,
            R.layout.item_exercise_type
        )
    }

    override fun onBindViewHolder(holder: ExercisesViewHolder, position: Int) {
        holder.bindItem(items[position])

        holder.initExerciseStatedStatus()

        holder.initOnClick()

        holder.checkIsExercisePerformed()
    }

    override fun getItemCount(): Int = items.size

    fun loadExercises(list: List<ExerciseTypeModel>) {
        Log.i("loadExercises", list.toString())
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun startExercise(
        startExercise: () -> Unit,
    ) {
        isExerciseStarted = true
        notifyDataSetChanged()
        startExercise()
    }

    fun successExercise(index: Int) {
        items[index].isPerformed = true
        notifyItemChanged(index)
    }

    inner class ExercisesViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int,
    ) : BaseViewHolder<ExerciseTypeModel, ItemExerciseTypeBinding>(itemId, parent, layoutRes) {
        fun initExerciseStatedStatus() {
            itemBinding.layoutExerciseStart.isVisible = isExerciseStarted
        }

        fun initOnClick() {
            itemView.setOnClickListener {
                if (!isExerciseStarted) {
                    onClickForUpdate(items[absoluteAdapterPosition])
                }
            }

            itemBinding.layoutExerciseStart.setOnClickListener {
                onClickForRecord(items[absoluteAdapterPosition])
            }
        }

        fun checkIsExercisePerformed() {
            if (items[absoluteAdapterPosition].isPerformed) {
                itemView.isSelected = true
            }
        }
    }

}


