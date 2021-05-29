package com.example.kakao_search.support

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("image")
fun bindCircleImage(imageView: ImageView, profileImage: Uri?) {
    Glide.with(imageView)
        .load(profileImage)
//        .placeholder(R.drawable.img_profile_basic)
//        .error(R.drawable.img_profile_basic)
        .into(imageView)
}
