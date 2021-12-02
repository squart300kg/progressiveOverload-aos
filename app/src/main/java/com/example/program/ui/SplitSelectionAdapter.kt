package com.example.program.ui

import android.content.Context
import android.text.TextUtils.split
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.databinding.ItemSplitSelectionBinding
import androidx.databinding.library.baseAdapters.BR

/**
 * Created by sangyoon on 2021/07/27
 */
class SplitSelectionAdapter(
): RecyclerView.Adapter<SplitSelectionAdapter.SplitViewHolder>() {

    private val items: MutableList<String> = mutableListOf("무분할", "2분할", "3분할", "4분할", "5분할")

    private val TAG = "HelpAdapterLog"
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

    }


    override fun getItemCount(): Int = items.size

    inner class SplitViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<String, ItemSplitSelectionBinding>(itemId, parent, layoutRes) {

    }

}


