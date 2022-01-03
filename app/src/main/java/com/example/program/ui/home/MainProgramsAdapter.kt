package com.example.program.ui.home

import android.util.Log
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.databinding.ItemProgramBinding
import com.example.program.model.entity.ProgramTable

/**
 * Created by sangyoon on 2021/07/27
 */
class MainProgramsAdapter(
    val startRegistration : (ProgramTable) -> Unit,
    val onClickMenu : (ProgramTable) -> Unit
): RecyclerView.Adapter<MainProgramsAdapter.ProgramViewHolder>() {

    private val items: MutableList<ProgramTable> = mutableListOf()

    private val TAG = "MainProgramsAdapter"
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProgramViewHolder {
        return ProgramViewHolder(
            BR.programItem,
            parent,
            R.layout.item_program
        )
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        holder.bindItem(items[position])

        holder.initOnClick()

    }

    fun loadPrograms(list: List<ProgramTable>) {
        Log.i("loadPrograms", list.toString())
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    inner class ProgramViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<ProgramTable, ItemProgramBinding>(itemId, parent, layoutRes) {
        fun initOnClick() {
            itemView.setOnClickListener {
                startRegistration(items[absoluteAdapterPosition])
            }

            itemBinding.ivMenu.setOnClickListener {
                onClickMenu(items[absoluteAdapterPosition])
            }
        }
    }
}


