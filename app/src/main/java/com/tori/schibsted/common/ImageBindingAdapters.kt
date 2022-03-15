package com.tori.schibsted.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tori.schibsted.R
import com.tori.schibsted.data.model.photo.PhotoDto

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, photo: PhotoDto?) {
    val options = RequestOptions.placeholderOf(R.drawable.ic_baseline_image_24).error(R.drawable.ic_baseline_image_24)
    val url = "https://farm${photo!!.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
    Glide.with(view).setDefaultRequestOptions(options).load(url).into(view)
}