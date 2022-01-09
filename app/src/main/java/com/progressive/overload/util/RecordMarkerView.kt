package com.progressive.overload.util

import android.app.Activity
import androidx.databinding.DataBindingUtil
import com.progressive.overload.databinding.LayoutGraphMarkerBinding
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils


class RecordMarkerView(activity: Activity, layoutResource: Int) :
    MarkerView(activity, layoutResource) {

    private var dataBinding: LayoutGraphMarkerBinding =
        DataBindingUtil.setContentView(activity, layoutResource)

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    override fun refreshContent(e: Entry, highlight: Highlight?) {
        if (e is CandleEntry) {
            val ce = e
            dataBinding.tvContent.text = "" + Utils.formatNumber(ce.high, 0, true)
        } else {
            dataBinding.tvContent.text = "" + Utils.formatNumber(e.y, 0, true)
        }
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }

}