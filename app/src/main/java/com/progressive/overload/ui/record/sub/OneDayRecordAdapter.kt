package com.progressive.overload.ui.record.sub

import android.util.Log
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.progressive.overload.R
import com.progressive.overload.base.BaseViewHolder
import com.progressive.overload.databinding.ItemReadRecordBinding
import com.progressive.overload.model.entity.RecordTable

/**
 * Created by sangyoon on 2021/07/27
 */
class OneDayRecordAdapter(
    val onClickForDetailSee: (String) -> Unit,
    val onClickForFold: (Int) -> Unit,
) : RecyclerView.Adapter<OneDayRecordAdapter.OneDayRecordViewHolder>() {

    private val items: MutableList<String> = mutableListOf()

    private lateinit var oneDayRecordViewHolder: OneDayRecordViewHolder
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OneDayRecordViewHolder {
        return OneDayRecordViewHolder(
            BR.exerciseName,
            parent,
            R.layout.item_read_record
        )
    }

    override fun onBindViewHolder(holder: OneDayRecordViewHolder, position: Int) {
        holder.bindItem(items[position])

        holder.initOnClick()
    }

    override fun getItemCount(): Int = items.size

    fun loadOneDayRecordName(list: List<String>) {
        Log.i("loadOneDayRecordName", list.toString())
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun loadOneDayRecordDetail(list: List<RecordTable>) {
        Log.i("loadOneDayRecordName", list.toString())
        oneDayRecordViewHolder.initRecordDetail(list)
    }

    inner class OneDayRecordViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int,
    ) : BaseViewHolder<String, ItemReadRecordBinding>(itemId, parent, layoutRes) {

        private var oneDayRecordDetailAdapter = OneDayRecordDetailAdapter()

        fun initOnClick() {
            itemBinding.layoutDetailSee.setOnClickListener {

                // 버튼 view변경
                itemBinding.layoutDetail.isVisible = true
                itemBinding.layoutDetailSee.isVisible = false

                // 뷰홀더 context저장
                oneDayRecordViewHolder = this

                // 클릭 이벤트
                onClickForDetailSee(items[absoluteAdapterPosition])
            }

            // 접기
            itemBinding.layoutFold.setOnClickListener {
                itemBinding.layoutDetail.isVisible = false
                itemBinding.layoutDetailSee.isVisible = true
                onClickForFold(absoluteAdapterPosition)
            }
        }

        fun initRecordDetail(list: List<RecordTable>) {
            itemBinding.rvRecordDetail.apply {
                setHasFixedSize(true)
                adapter = oneDayRecordDetailAdapter
            }
            oneDayRecordDetailAdapter.loadOneDayRecordDetail(list)
        }
    }

}


