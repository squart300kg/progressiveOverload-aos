package com.progressive.overload.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.progressive.overload.util.GlideUtil

@BindingAdapter("theWarsOfStar:setImage")
fun ImageView.setImage(thumbnailURL: String) {

    GlideUtil.loadImage(this, thumbnailURL)

}