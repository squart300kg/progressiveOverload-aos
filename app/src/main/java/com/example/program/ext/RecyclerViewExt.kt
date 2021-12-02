package com.example.program.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("setItems")
fun <T> RecyclerView.setItems(items: List<T>?) {
    items?.let {
//        when(adapter) {
//            is GamerListAdapter -> {
//                (adapter as GamerListAdapter).loadGamerList(items as List<GamerItem>)
//            }
//            is BannerAdapter -> {
//                (adapter as BannerAdapter).loadBannerList(items as List<BannerItem>)
//            }
//            is ReviewAdapter -> {
//                (adapter as ReviewAdapter).loadReviewList(items as List<ReviewItem>)
//            }
//            is FreeLecturesAdapter -> {
//                (adapter as FreeLecturesAdapter).loadLectureList(items as List<FreeLecturesResponse.Item>)
//            }
////            is ChattingAdapter -> {
////                (adapter as ChattingAdapter).loadAllBallon(items as List<ChattingItem>)
////            }
//
//        }
    }
}