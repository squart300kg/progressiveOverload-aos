package com.example.program.ui.home.sub

import android.util.Log
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.constant.Split
import com.example.program.databinding.ItemSplitSelectionBinding

/**
 * Created by sangyoon on 2021/07/27
 */
class SplitSelectionAdapter(
    val startRegistration : (Split) -> Unit
): RecyclerView.Adapter<SplitSelectionAdapter.SplitViewHolder>() {

    private val items: MutableList<Split> = mutableListOf(
        Split.NO_SPLIT,
        Split.TWO_SPLIT,
        Split.THREE_SPLIT,
        Split.FOUR_SPLIT,
        Split.FIVE_SPLIT,
        Split.SIX_SPLIT,
        Split.SEVEN_SPLIT,
        Split.EIGHT_SPLIT,
        Split.NINE_SPLIT,
        Split.TEN_SPLIT,
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SplitViewHolder {
        return SplitViewHolder(
            BR.splitItem,
            parent,
            R.layout.item_split_selection
        )
    }

    override fun onBindViewHolder(holder: SplitViewHolder, position: Int) {
        holder.bindItem(items[position])

        holder.itemView.setOnClickListener {
            startRegistration(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    inner class SplitViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<Split, ItemSplitSelectionBinding>(itemId, parent, layoutRes) {

    }

}


