package com.example.program.ui.home.sub

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.databinding.ItemWriteRecordBinding
import com.example.program.model.model.RecordExerciseModel
import com.example.program.util.InputFilterMinMax


/**
 * Created by sangyoon on 2021/07/27
 */
class RecordExerciseAdapter(
    private val context: Context,
    private val exerciseName: String?,
    private val onClickForSuccess: (recordItem: RecordExerciseModel) -> Unit,
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
            R.layout.item_write_record
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
    ) : BaseViewHolder<RecordExerciseModel, ItemWriteRecordBinding>(itemId, parent, layoutRes) {


        fun initOnClick() {
            itemBinding.tvExerciseComplete.setOnClickListener {

                if (!itemBinding.animationFloating.isVisible) {
                    recordExViewHolder = this

                    onClickForSuccess(RecordExerciseModel(
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
            itemBinding.animationFloating.isVisible = true
            itemBinding.etWeight.inputType = InputType.TYPE_NULL
            itemBinding.etRepitition.inputType = InputType.TYPE_NULL
            itemBinding.etRpe.inputType = InputType.TYPE_NULL
            itemBinding.etRestTime.inputType = InputType.TYPE_NULL
            successCount++

            if (successCount == items.size)
                Toast.makeText(context, "$exerciseName 완료!", Toast.LENGTH_LONG).show()
        }

        fun checkIsExercisePerformed() {
            if (items[absoluteAdapterPosition].isPerformed) {
                itemBinding.animationFloating.isVisible = true
                itemBinding.etWeight.inputType = InputType.TYPE_NULL
                itemBinding.etRepitition.inputType = InputType.TYPE_NULL
                itemBinding.etRpe.inputType = InputType.TYPE_NULL
                itemBinding.etRestTime.inputType = InputType.TYPE_NULL
                successCount++
            }
        }
    }

}


