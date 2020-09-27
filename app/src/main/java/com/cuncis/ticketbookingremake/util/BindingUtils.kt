package com.cuncis.ticketbookingremake.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("loadImageUrl")
fun ImageView.loadImageUrl(url: String?) {
    Glide.with(this.context)
        .load(url.toString())
        .centerCrop()
        .into(this)
}