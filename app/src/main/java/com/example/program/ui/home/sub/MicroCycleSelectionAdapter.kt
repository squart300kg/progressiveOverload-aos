package com.example.program.ui.home.sub

import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.constant.MesoCycleSplit
import com.example.program.constant.MicroCycleSplit
import com.example.program.databinding.ItemMesoCycleSplitSelectionBinding
import com.example.program.databinding.ItemMicroCycleSplitSelectionBinding

/**
 * Created by sangyoon on 2021/07/27
 */
class MicroCycleSelectionAdapter(
    val onClick : (MicroCycleSplit) -> Unit
): RecyclerView.Adapter<MicroCycleSelectionAdapter.SplitViewHolder>() {

    private val items: MutableList<MicroCycleSplit> = mutableListOf(
        MicroCycleSplit.NO_SPLIT,
        MicroCycleSplit.TWO_SPLIT,
        MicroCycleSplit.THREE_SPLIT,
        MicroCycleSplit.FOUR_SPLIT,
        MicroCycleSplit.FIVE_SPLIT,
        MicroCycleSplit.SIX_SPLIT,
        MicroCycleSplit.SEVEN_SPLIT,
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SplitViewHolder {
        return SplitViewHolder(
            BR.splitItem,
            parent,
            R.layout.item_micro_cycle_split_selection
        )
    }

    override fun onBindViewHolder(holder: SplitViewHolder, position: Int) {
        holder.bindItem(items[position])

        holder.itemView.setOnClickListener {
            onClick(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    inner class SplitViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<MicroCycleSplit, ItemMicroCycleSplitSelectionBinding>(itemId, parent, layoutRes) {

    }

}


