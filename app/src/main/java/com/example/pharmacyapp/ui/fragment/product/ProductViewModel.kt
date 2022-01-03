package com.example.pharmacyapp.ui.fragment.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    application: Application,
    val repository: Repository
) : AndroidViewModel(application) {

    val photos = listOf<Photo>(
        Photo(
            description = "",
            id = 1,
            url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png"
        ),
        Photo(
            description = "",
            id = 2,
            url = "https://developer.android.com/images/social/android-developers.png"
        ),
        Photo(
            description = "",
            id = 3,
            url = "https://www.itsfoss.net/wp-content/uploads/2021/10/Android.jpg"
        )
    )

}