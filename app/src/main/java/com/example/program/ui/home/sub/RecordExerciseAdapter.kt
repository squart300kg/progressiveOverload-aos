package com.example.program.ui.home.sub

import android.util.Log
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.constant.Split
import com.example.program.databinding.ItemRecordExerciseBinding
import com.example.program.databinding.ItemSplitSelectionBinding
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.model.RecordExerciseModel

/**
 * Created by sangyoon on 2021/07/27
 */
class RecordExerciseAdapter(
    val onClick : () -> Unit
): RecyclerView.Adapter<RecordExerciseAdapter.RecordExViewHolder>() {

    private val items: MutableList<RecordExerciseModel> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecordExViewHolder {
        return RecordExViewHolder(
            BR.recordItem,
            parent,
            R.layout.item_record_exercise
        )
    }

    override fun onBindViewHolder(holder: RecordExViewHolder, position: Int) {
        holder.bindItem(items[position])

        holder.itemView.setOnClickListener {
            onClick()
        }
    }

    override fun getItemCount(): Int = items.size

    fun loadRecord(list: List<RecordExerciseModel>) {
        Log.i("loadRecord1", list.toString())
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class RecordExViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<RecordExerciseModel, ItemRecordExerciseBinding>(itemId, parent, layoutRes) {

    }

}


