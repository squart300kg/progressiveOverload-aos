package com.example.program.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.smarteist.autoimageslider.IndicatorView.utils.DensityUtils

class ItemDecoration(
    private val left : Int = 0,
    private val top : Int = 0,
    private val right : Int = 0,
    private val bottom : Int = 0,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = DensityUtils.dpToPx(left)
        outRect.top = DensityUtils.dpToPx(top)
        outRect.right = DensityUtils.dpToPx(right)
        outRect.bottom = DensityUtils.dpToPx(bottom)
    }
}