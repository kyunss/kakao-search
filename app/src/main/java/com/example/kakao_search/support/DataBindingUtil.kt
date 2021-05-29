package com.example.kakao_search.support

import android.net.Uri
import android.os.Build
import android.text.Html
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.kakao_search.R
import java.time.LocalDate
import java.time.LocalDateTime


@BindingAdapter("image")
fun bindCircleImage(imageView: ImageView, image: Uri) {
    Glide.with(imageView)
        .load(image)
        .error(R.drawable.ic_baseline_disabled_by_default_24)
        .into(imageView)
}

@BindingAdapter("htmlText")
fun bindHtmlText(textView: AppCompatTextView, htmlText: String) {
    textView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(htmlText)
    }
}

@BindingAdapter("dateFormat")
fun bindDateFormat(textView: AppCompatTextView, dateText: String) {
    val localDate = LocalDate.parse(dateText)
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)

    textView.text = when {
        localDate.isEqual(today) -> textView.context.getString(R.string.today)

        localDate.isEqual(yesterday) -> textView.context.getString(R.string.yesterday)

        else -> dateText
    }
}


