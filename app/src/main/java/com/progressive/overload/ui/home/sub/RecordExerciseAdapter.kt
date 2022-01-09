package com.progressive.overload.ui.home.sub

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.progressive.overload.R
import com.progressive.overload.base.BaseViewHolder
import com.progressive.overload.databinding.ItemWriteRecordBinding
import com.progressive.overload.model.model.RecordExerciseModel
import com.progressive.overload.util.InputFilterMinMax


/**
 * Created by sangyoon on 2021/07/27
 */
class RecordExerciseAdapter(
    private val context: Context,
    private val onClickForSuccess: (recordItem: RecordExerciseModel) -> Unit,
    private val onCompleteExercise: () -> Unit,
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

                    if (!itemBinding.etWeight.text.isNullOrEmpty() &&
                        !itemBinding.etRepitition.text.isNullOrEmpty() &&
                        !itemBinding.etRpe.text.isNullOrEmpty() &&
                        !itemBinding.etRestTime.text.isNullOrEmpty()) {
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

            // 각 요소 클릭시 각 요소에 포커스 맞추기
            itemBinding.etWeight.setOnClickListener {
                itemBinding.etWeight.requestFocus()
            }
            itemBinding.etRepitition.setOnClickListener {
                itemBinding.etRepitition.requestFocus()
            }
            itemBinding.etRpe.setOnClickListener {
                itemBinding.etRpe.requestFocus()
            }
            itemBinding.etRestTime.setOnClickListener {
                itemBinding.etRestTime.requestFocus()
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
                onCompleteExercise()
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


