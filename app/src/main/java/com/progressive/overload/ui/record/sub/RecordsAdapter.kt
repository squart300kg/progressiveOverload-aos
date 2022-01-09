package com.progressive.overload.ui.record.sub

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.progressive.overload.R
import com.progressive.overload.base.BaseViewHolder
import com.progressive.overload.databinding.ItemRecordsBinding
import com.progressive.overload.model.model.ExerciseVolumeModel
import com.progressive.overload.model.model.RecordModel

/**
 * Created by sangyoon on 2021/07/27
 */
class RecordsAdapter(
    val context: Context,
    val onClickForDetailSee: (String) -> Unit,
    val onClickForMoreDetailSee: (String) -> Unit,
    val onClickForFold: (Int) -> Unit,
) : RecyclerView.Adapter<RecordsAdapter.RecordViewHolder>() {

    private val items: MutableList<RecordModel> = mutableListOf()

    private lateinit var recordViewHolder: RecordViewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecordViewHolder {
        return RecordViewHolder(
            BR.recordItem,
            parent,
            R.layout.item_records
        )
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bindItem(items[position])

        holder.initOnClick()
    }

    override fun getItemCount(): Int = items.size

    fun loadRecords(list: List<RecordModel>) {
        Log.i("loadRecords", list.toString())
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun loadExerciseVolumes(list : List<ExerciseVolumeModel>) {
        recordViewHolder.initExerciseVolumes(list)
    }

    inner class RecordViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int,
    ) : BaseViewHolder<RecordModel, ItemRecordsBinding>(itemId, parent, layoutRes) {

        private var exerciseVolumeAdapter = ExerciseVolumeAdapter()

        fun initOnClick() {

            // 자세히 보기
            itemBinding.layoutDetailSee.setOnClickListener {
                // 버튼 view변경
                itemBinding.layoutDetail.isVisible = true
                itemBinding.layoutDetailSee.isVisible = false

                // 뷰홀더 context저장
                recordViewHolder = this

                // 클릭 이벤트
                onClickForDetailSee(items[absoluteAdapterPosition].recordTime)
            }

            // 기록 상세 보기
            itemBinding.tvDetailSeeSecond.setOnClickListener {
                onClickForMoreDetailSee(
                    items[absoluteAdapterPosition].recordTime)
            }

            // 접기
            itemBinding.layoutFold.setOnClickListener {
                itemBinding.layoutDetail.isVisible = false
                itemBinding.layoutDetailSee.isVisible = true
                onClickForFold(absoluteAdapterPosition)
            }


        }

        fun initExerciseVolumes(list: List<ExerciseVolumeModel>) {
            itemBinding.rvExerciseVolumes.apply {
                setHasFixedSize(true)
                adapter = exerciseVolumeAdapter
            }
            exerciseVolumeAdapter.loadExerciseVolumes(list)
        }
    }
}


