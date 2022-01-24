package com.progressive.overload.ui.home

import android.util.Log
import android.view.ViewGroup
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
class MainProgramsAdapter(
    val startRegistration : (HomeProgramModel) -> Unit,
    val onClickForDupl : (HomeProgramModel) -> Unit,
    val onClickForChange : (HomeProgramModel) -> Unit,
    val onClickForDelete : (HomeProgramModel) -> Unit,
): RecyclerView.Adapter<MainProgramsAdapter.ProgramViewHolder>() {

    private val items: MutableList<HomeProgramModel> = mutableListOf()

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

    fun loadPrograms(list: List<HomeProgramModel>) {
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
    ): BaseViewHolder<HomeProgramModel, ItemProgramBinding>(itemId, parent, layoutRes) {
        fun initOnClick() {
            itemView.setOnClickListener {
                startRegistration(items[absoluteAdapterPosition])
            }

            itemBinding.tvDupl.setOnClickListener {
                onClickForDupl(items[absoluteAdapterPosition])
            }

            itemBinding.tvChange.setOnClickListener {
                onClickForChange(items[absoluteAdapterPosition])
            }

            itemBinding.tvDelete.setOnClickListener {
                onClickForDelete(items[absoluteAdapterPosition])
            }
        }
    }
}


