//package com.example.program.util
//
//import android.opengl.ETC1.getHeight
//
//import android.opengl.ETC1.getWidth
//
//import com.github.mikephil.charting.utils.MPPointF
//
//import com.github.mikephil.charting.data.CandleEntry
//
//import android.R
//import android.content.Context
//import android.view.View
//
//import android.widget.TextView
//
//import com.github.mikephil.charting.components.MarkerView
//import com.github.mikephil.charting.data.Entry
//import com.github.mikephil.charting.highlight.Highlight
//import com.github.mikephil.charting.utils.Utils
//
//
//class RecordMarkerView(context: Context?, layoutResource: Int) :
//    MarkerView(context, layoutResource) {
//
//    private val tvContent: TextView
//
//    // callbacks everytime the MarkerView is redrawn, can be used to update the
//    // content (user-interface)
//    override fun refreshContent(e: Entry, highlight: Highlight?) {
//        if (e is CandleEntry) {
//            val ce = e as CandleEntry
//            tvContent.text = "" + Utils.formatNumber(ce.high, 0, true)
//        } else {
//            tvContent.text = "" + Utils.formatNumber(e.getY(), 0, true)
//        }
//        super.refreshContent(e, highlight)
//    }
//
//    override fun getOffset(): MPPointF {
//        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
//    }
//
//    init {
//        tvContent = findViewById<TextView>(R.id.tvContent)
//    }
//}