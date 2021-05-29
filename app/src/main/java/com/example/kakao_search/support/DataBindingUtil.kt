package com.example.kakao_search.support

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.kakao_search.R


@BindingAdapter("image")
fun bindCircleImage(imageView: ImageView, image: Uri) {
    Glide.with(imageView)
        .load(image)
        .error(R.drawable.ic_baseline_disabled_by_default_24)
        .into(imageView)
}
