package com.example.pharmacyapp.util

import android.graphics.Color
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pharmacyapp.R
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("imageUrl")
fun bindImage(
    imageView: ImageView,
    imgUrl: String?
) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("http").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_image_animation)
                    .error(R.drawable.image_not_found)
            )
            .into(imageView)
    }
}

@BindingAdapter("priceText")
fun bindTextView(textView: MaterialTextView, price: Int) {
    val priceText = "$price تومان "
    textView.text = priceText
}

@BindingAdapter("stockText")
fun bindStockTextView(textView: MaterialTextView, stock: Int) {
    if (stock == 0) {
        textView.text = "در انبار موجود نیست"
        textView.setTextColor(Color.parseColor("#B81111"))
    } else {
        textView.text = "موجود در انبار"
        textView.setTextColor(Color.parseColor("#569C06"))
    }
}

@BindingAdapter("needPrescription")
fun bindNeedPrescription(textView: MaterialTextView, need_dr: Boolean) {
    if (need_dr) {
        textView.text = "بدون نیاز به نسخه پزشک"
        textView.setTextColor(Color.parseColor("#569C06"))
    } else {
        textView.text = "نیازمند نسخه پزشک"
        textView.setTextColor(Color.parseColor("#B81111"))
    }
}