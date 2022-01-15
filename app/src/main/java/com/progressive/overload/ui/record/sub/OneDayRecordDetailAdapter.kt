package com.progressive.overload.ui.record.sub

import android.util.Log
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.progressive.overload.R
import com.progressive.overload.base.BaseViewHolder
import com.progressive.overload.databinding.ItemReadDetailRecordBinding
import com.progressive.overload.model.entity.RecordTable

/**
 * Created by sangyoon on 2021/07/27
 */
class OneDayRecordDetailAdapter :
    RecyclerView.Adapter<OneDayRecordDetailAdapter.OneDayRecordViewHolder>() {

    private val items: MutableList<RecordTable> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OneDayRecordViewHolder {
        return OneDayRecordViewHolder(
            BR.recordItem,
            parent,
            R.layout.item_read_detail_record
        )
    }

    override fun onBindViewHolder(holder: OneDayRecordViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun loadOneDayRecordDetail(list: List<RecordTable>) {
        Log.i("loadOneDayRecordDetail", list.toString())
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class OneDayRecordViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int,
    ) : BaseViewHolder<RecordTable, ItemReadDetailRecordBinding>(itemId, parent, layoutRes)

}


