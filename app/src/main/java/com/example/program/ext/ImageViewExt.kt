package com.example.program.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.program.util.GlideUtil

@BindingAdapter("theWarsOfStar:setImage")
fun ImageView.setImage(thumbnailURL: String) {

    GlideUtil.loadImage(this, thumbnailURL)

}