package com.progressive.overload.ui.record

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.progressive.overload.R
import com.progressive.overload.base.BaseViewHolder
import com.progressive.overload.databinding.ItemProgramBinding
import com.progressive.overload.model.entity.ProgramTable
import com.progressive.overload.model.model.HomeProgramModel

/**
 * Created by sangyoon on 2021/07/27
 */
class ProgramAdapter(
    val context: Context,
    val onClick: (Long) -> Unit,
) : RecyclerView.Adapter<ProgramAdapter.RecordsViewHolder>() {

    private val items: MutableList<HomeProgramModel> = mutableListOf()

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

        holder.initValues()

        holder.initOnClick()
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int) = position
    
    fun loadPrograms(list: List<HomeProgramModel>) {
        Log.i("loadPrograms", list.toString())
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class RecordsViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int,
    ) : BaseViewHolder<HomeProgramModel, ItemProgramBinding>(itemId, parent, layoutRes) {
        fun initOnClick() {
            itemView.setOnClickListener {
                onClick(items[absoluteAdapterPosition].no)
            }
        }

        fun initValues() {
            itemBinding.ivMenu.isVisible = false
        }
    }

}


