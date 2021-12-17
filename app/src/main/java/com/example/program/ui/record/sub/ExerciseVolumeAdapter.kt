package com.example.program.ui.record.sub

import android.util.Log
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.program.R
import com.example.program.base.BaseViewHolder
import com.example.program.databinding.ItemExerciseVolumeBinding
import com.example.program.model.model.ExerciseVolumeModel

/**
 * Created by sangyoon on 2021/07/27
 */
class ExerciseVolumeAdapter : RecyclerView.Adapter<ExerciseVolumeAdapter.VolumeViewHolder>() {

    private val items: MutableList<ExerciseVolumeModel> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): VolumeViewHolder {
        return VolumeViewHolder(
            BR.volumeItem,
            parent,
            R.layout.item_exercise_volume
        )
    }

    override fun onBindViewHolder(holder: VolumeViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun loadExerciseVolumes(list: List<ExerciseVolumeModel>) {
        Log.i("loadExerciseVolumes", list.toString())
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class VolumeViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int,
    ) : BaseViewHolder<ExerciseVolumeModel, ItemExerciseVolumeBinding>(itemId, parent, layoutRes)

}


