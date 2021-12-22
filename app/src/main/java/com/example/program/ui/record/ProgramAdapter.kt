package com.example.program.ui.record

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.databinding.ItemExerciseTypeBinding
import com.example.program.model.entity.ProgramTable
import com.example.program.model.entity.RecordTable

/**
 * Created by sangyoon on 2021/07/27
 */
class ProgramAdapter(
    val context: Context,
    val onClick: (Long) -> Unit,
) : RecyclerView.Adapter<ProgramAdapter.RecordsViewHolder>() {

    private val items: MutableList<ProgramTable> = mutableListOf()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecordsViewHolder {
        return RecordsViewHolder(
            BR.programItem,
            parent,
            R.layout.item_program
        )
    }

    override fun onBindViewHolder(holder: RecordsViewHolder, position: Int) {
        holder.bindItem(items[position])

        holder.initOnClick()
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int) = position
    
    fun loadPrograms(list: List<ProgramTable>) {
        Log.i("loadPrograms", list.toString())
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class RecordsViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int,
    ) : BaseViewHolder<ProgramTable, ItemExerciseTypeBinding>(itemId, parent, layoutRes) {
        fun initOnClick() {
            itemView.setOnClickListener {
                onClick(items[absoluteAdapterPosition].no)
            }
        }
    }

}


