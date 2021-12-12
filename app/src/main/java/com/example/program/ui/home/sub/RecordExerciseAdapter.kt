package com.example.program.ui.home.sub

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.text.InputFilter
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.databinding.ItemRecordExerciseBinding
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.model.RecordExerciseModel
import com.example.program.util.InputFilterMinMax


/**
 * Created by sangyoon on 2021/07/27
 */
class RecordExerciseAdapter(
    private val context: Context,
    private val onClick: (recordItem: RecordExerciseModel) -> Unit,
) : RecyclerView.Adapter<RecordExerciseAdapter.RecordExViewHolder>() {

    private val items: MutableList<RecordExerciseModel> = mutableListOf()

    private lateinit var recordExViewHolder: RecordExViewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecordExViewHolder {
        return RecordExViewHolder(
            context,
            BR.recordItem,
            parent,
            R.layout.item_record_exercise
        )
    }

    override fun onBindViewHolder(holder: RecordExViewHolder, position: Int) {
        holder.bindItem(items[position])

        holder.initRpeRange()

        holder.initOnClick()
    }

    override fun getItemCount(): Int = items.size

    fun loadRecord(list: List<ExerciseTypeTable>) {
        val items = mutableListOf<RecordExerciseModel>()
        for (index in 0 until list[0].setNum!!) {
            items.add(
                RecordExerciseModel(
                    index,
                    list[0].weight,
                    list[0].repitition,
                    list[0].setNum,
                    8,
                    list[0].restTime
                )
            )
        }

        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun successExercise() {
        recordExViewHolder.successExercise()
    }

    inner class RecordExViewHolder(
        context: Context,
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int,
    ) : BaseViewHolder<RecordExerciseModel, ItemRecordExerciseBinding>(itemId, parent, layoutRes) {
        fun initOnClick() {
            itemBinding.tvExerciseComplete.setOnClickListener {

                if (!itemView.isSelected) {
                    recordExViewHolder = this

                    onClick(RecordExerciseModel(
                        items[absoluteAdapterPosition].no,
                        itemBinding.etWeight.text.toString().toInt(),
                        itemBinding.etRepitition.text.toString().toInt(),
                        items[absoluteAdapterPosition].setNum,
                        itemBinding.etRpe.text.toString().toInt(),
                        itemBinding.etRestTime.text.toString().toInt()
                    ))
                }
            }
        }

        fun initRpeRange() {
            itemBinding.etRpe.filters = arrayOf<InputFilter>(InputFilterMinMax("1", "10"))
        }

        fun successExercise() {
            Log.i("regSuccess", "success")
            itemView.isSelected = true
        }

    }

}


