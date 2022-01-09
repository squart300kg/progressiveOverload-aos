package com.progressive.overload.ui.home.sub

import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.progressive.overload.R
import com.progressive.overload.base.BaseViewHolder
import com.progressive.overload.constant.MesoCycleSplit
import com.progressive.overload.databinding.ItemMesoCycleSplitSelectionBinding

/**
 * Created by sangyoon on 2021/07/27
 */
class MesoCycleSelectionAdapter(
    val onClick : (MesoCycleSplit) -> Unit
): RecyclerView.Adapter<MesoCycleSelectionAdapter.SplitViewHolder>() {

    private val items: MutableList<MesoCycleSplit> = mutableListOf(
        MesoCycleSplit.NO_SPLIT,
        MesoCycleSplit.TWO_SPLIT,
        MesoCycleSplit.THREE_SPLIT,
        MesoCycleSplit.FOUR_SPLIT,
        MesoCycleSplit.FIVE_SPLIT,
        MesoCycleSplit.SIX_SPLIT,
        MesoCycleSplit.SEVEN_SPLIT,
        MesoCycleSplit.EIGHT_SPLIT,
        MesoCycleSplit.NINE_SPLIT,
        MesoCycleSplit.TEN_SPLIT,
        MesoCycleSplit.ELEVEN_SPLIT,
        MesoCycleSplit.TWELVE_SPLIT,
        MesoCycleSplit.THIRTEEN_SPLIT,
        MesoCycleSplit.FOURTEEN_SPLIT,
        MesoCycleSplit.FIFTEEN_SPLIT,
        MesoCycleSplit.SIXTEEN_SPLIT,
        MesoCycleSplit.SEVENTEEN_SPLIT,
        MesoCycleSplit.EIGHTEEN_SPLIT,
        MesoCycleSplit.NINETEEN_SPLIT,
        MesoCycleSplit.TWENTY_SPLIT,
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SplitViewHolder {
        return SplitViewHolder(
            BR.splitItem,
            parent,
            R.layout.item_meso_cycle_split_selection
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
    ): BaseViewHolder<MesoCycleSplit, ItemMesoCycleSplitSelectionBinding>(itemId, parent, layoutRes) {

    }

}


