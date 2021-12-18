package com.example.program.ui.record.sub

import android.util.Log
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.databinding.ItemReadRecordBinding
import com.example.program.model.entity.RecordTable

/**
 * Created by sangyoon on 2021/07/27
 */
class OneDayRecordAdapter : RecyclerView.Adapter<OneDayRecordAdapter.OneDayRecordViewHolder>() {

    private val items: MutableList<RecordTable> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OneDayRecordViewHolder {
        return OneDayRecordViewHolder(
            BR.recordItem,
            parent,
            R.layout.item_read_record
        )
    }

    override fun onBindViewHolder(holder: OneDayRecordViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun loadOneDayRecord(list: List<RecordTable>) {
        Log.i("loadOneDayRecord", list.toString())
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class OneDayRecordViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int,
    ) : BaseViewHolder<RecordTable, ItemReadRecordBinding>(itemId, parent, layoutRes)

}


