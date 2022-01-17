package com.example.pharmacyapp.util

import android.graphics.Color
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pharmacyapp.R
import com.example.pharmacyapp.adapter.*
import com.example.pharmacyapp.model.Category
import com.example.pharmacyapp.model.Medicine
import com.example.pharmacyapp.model.Photo
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("listDataCategory")
fun bindRecyclerViewCategory(recyclerView: RecyclerView, data: List<Category>?) {
    val adapter = recyclerView.adapter as CategoryAdapter
    adapter.submitList(data)
}

@BindingAdapter("listDataMedicine")
fun bindRecyclerViewMedicine(recyclerView: RecyclerView, data: List<Medicine>?) {
    val adapter = recyclerView.adapter as MedicineAdapter
    adapter.submitList(data)
}

@BindingAdapter("listDataVerticalMedicine")
fun bindRecyclerViewVerticalMedicine(recyclerView: RecyclerView, data: List<Medicine>?) {
    val adapter = recyclerView.adapter as MedicineVerticalAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(
    imageView: ImageView,
    imgUrl: String?
) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
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
        textView.setTextColor(Color.RED)
    } else {
        textView.text = "موجود در انبار"
        textView.setTextColor(Color.GREEN)
    }
}

@BindingAdapter("needPrescription")
fun bindNeedPrescription(textView: MaterialTextView, need_dr: Boolean) {
    if (need_dr) {
        textView.text = "بدون نیاز به نسخه پزشک"
        textView.setTextColor(Color.GREEN)
    } else {
        textView.text = "نیازمند نسخه پزشک"
        textView.setTextColor(Color.RED)
    }
}

@BindingAdapter("imageViewPagerData")
fun bindImageViewPager(viewPager2: ViewPager2, data: List<Photo>?) {
    val adapter = viewPager2.adapter as ImageViewPagerAdapter
    adapter.submitList(data)
}
