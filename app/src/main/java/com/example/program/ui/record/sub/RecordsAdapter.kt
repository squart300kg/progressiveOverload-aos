package com.example.program.ui.record.sub

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.databinding.ItemExerciseTypeBinding
import com.example.program.databinding.ItemRecordsBinding
import com.example.program.model.model.RecordModel

/**
 * Created by sangyoon on 2021/07/27
 */
class RecordsAdapter(
    val context : Context,
    val onClick : (String) -> Unit
) : RecyclerView.Adapter<RecordsAdapter.RecordViewHolder>() {

    private val items: MutableList<RecordModel> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecordViewHolder {
        return RecordViewHolder(
            BR.recordItem,
            parent,
            R.layout.item_records
        )
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bindItem(items[position])

        holder.initOnClick()
    }

    override fun getItemCount(): Int = items.size

    fun loadRecords(list: List<RecordModel>) {
        Log.i("loadRecords", list.toString())
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


    inner class RecordViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int,
    ) : BaseViewHolder<RecordModel, ItemRecordsBinding>(itemId, parent, layoutRes) {
        fun initOnClick() {
            itemBinding.tvDetailSeeSecond.setOnClickListener {
                onClick(items[absoluteAdapterPosition].recordTime)
            }

            itemBinding.layoutDetailSee.setOnClickListener {
                itemBinding.layoutDetail.isVisible = true
                itemBinding.layoutDetailSee.isVisible = false
            }

            itemBinding.layoutFold.setOnClickListener {
                itemBinding.layoutDetail.isVisible = false
                itemBinding.layoutDetailSee.isVisible = true
            }



        }


    }

}


