package com.progressive.overload.ext

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

/**
 * Created by sangyoon on 2021/09/02
 */
@BindingAdapter("overload:setVisibility")
fun View.setVisibility(isVisible: Boolean) {
    this.isVisible = isVisible
}