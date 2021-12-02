package com.example.program.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.program.R
import com.smarteist.autoimageslider.IndicatorView.utils.DensityUtils

object GlideUtil {

    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView)
            .load(url)
            .apply(
                RequestOptions.bitmapTransform(
                    MultiTransformation(
                        CenterCrop(),
                        RoundedCorners(DensityUtils.dpToPx(6))
                    )
                )
            )
            .placeholder(R.color.black)
            .error(R.color.black)
            .into(imageView)
    }
}