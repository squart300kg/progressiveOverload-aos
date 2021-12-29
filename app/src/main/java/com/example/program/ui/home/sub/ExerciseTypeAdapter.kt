package com.example.program.ui.home.sub

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.databinding.ItemExerciseTypeBinding
import com.example.program.model.model.ExerciseTypeModel

/**
 * Created by sangyoon on 2021/07/27
 */
class ExerciseTypeAdapter(
    val context: Context,
    val onClickForRecord: (item: ExerciseTypeModel) -> Unit,
    val onClickForMenu: (item: ExerciseTypeModel, position: Int) -> Unit,
) : RecyclerView.Adapter<ExerciseTypeAdapter.ExercisesViewHolder>() {

    private val items: MutableList<ExerciseTypeModel> = mutableListOf()

    private var isExerciseStarted = false

    private var performedExerciseCounter = 0

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

    override fun getItemViewType(position: Int) = position

    fun loadExercises(list: List<ExerciseTypeModel>) {
        Log.i("statuses", list.toString())
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun removeTargetedItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun initExerciseStatus(
        status: Boolean,
        startExercise: () -> Unit,
    ) {
        isExerciseStarted = status
        notifyDataSetChanged()
        startExercise()
    }

    inner class ExercisesViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int,
    ) : BaseViewHolder<ExerciseTypeModel, ItemExerciseTypeBinding>(itemId, parent, layoutRes) {
        fun initExerciseStatedStatus() {
            itemBinding.layoutExerciseStart.isVisible = isExerciseStarted
            itemBinding.ivMenu.isVisible = !isExerciseStarted
        }

        fun initOnClick() {
            itemBinding.layoutExerciseStart.setOnClickListener {
                onClickForRecord(items[absoluteAdapterPosition])
            }

            itemBinding.ivMenu.setOnClickListener {
                onClickForMenu(items[absoluteAdapterPosition], absoluteAdapterPosition)
            }
        }

        fun checkIsExercisePerformed() {
            if (items[absoluteAdapterPosition].isPerformed) {
                Log.i("statuses", "adapter - isPerform[$absoluteAdapterPosition] : ${items[absoluteAdapterPosition].isPerformed}")

                itemView.isSelected = true
            }
        }
    }

    companion object {
        const val START = true
        const val STOP = false
    }
}


