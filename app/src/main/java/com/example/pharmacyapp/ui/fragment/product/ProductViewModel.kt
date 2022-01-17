package com.example.pharmacyapp.ui.fragment.product

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.model.Medicine
import com.example.pharmacyapp.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    application: Application,
    private val repository: Repository,
    private val state: SavedStateHandle
) : AndroidViewModel(application) {

    //test
    init {
        val productId: Int? = state.get<Int>("productId")
        Log.i("testArgs", "$productId")
    }


    val medicine = Medicine(
        id = 9,
        name = "دارو 9",
        imageUri = "",
        price = 15000,
        stock = 10,
        company = "ایزان دارو",
        usage = "بیماری های قلبی",
        keeping = "در دمای معمولی دور از نور خورشید",
        guide = "با توجه به نسخه پزشک مصرف شود",
        need_dr = false,
        images = listOf<Photo>(
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
    )

}