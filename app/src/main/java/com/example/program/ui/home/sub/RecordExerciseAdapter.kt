package com.example.program.ui.home.sub

import android.content.Context
import android.text.InputFilter
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.databinding.ItemRecordExerciseBinding
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.RecordTable
import com.example.program.model.model.RecordExerciseModel
import com.example.program.util.InputFilterMinMax


/**
 * Created by sangyoon on 2021/07/27
 */
class RecordExerciseAdapter(
    private val context: Context,
    private val exerciseName: String?,
    private val onClick: (recordItem: RecordExerciseModel) -> Unit,
) : RecyclerView.Adapter<RecordExerciseAdapter.RecordExViewHolder>() {

    private val items: MutableList<RecordExerciseModel> = mutableListOf()

    private lateinit var recordExViewHolder: RecordExViewHolder

    private var successCount = 0

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

        holder.checkIsExercisePerformed()
    }

    override fun getItemCount(): Int = items.size

    fun loadRecord(list: List<RecordExerciseModel>) {
        items.clear()
        items.addAll(list)
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
            itemView.isSelected = true
            successCount++
            if (successCount == items.size)
                Toast.makeText(context, "$exerciseName 완료!", Toast.LENGTH_LONG).show()
        }

        fun checkIsExercisePerformed() {
            if (items[absoluteAdapterPosition].isPerformed) {
                itemView.isSelected = true
                successCount++
            }
        }
    }

}


