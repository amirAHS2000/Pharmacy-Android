package com.example.pharmacyapp.ui.fragment.medicines

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.model.Medicine
import com.example.pharmacyapp.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val repository: Repository,
    application: Application,
    private val state: SavedStateHandle
) : AndroidViewModel(application) {


    //test
    init {
        val productId: Int? = state.get<Int>("medCategoryId")
        Log.i("testArgs", "$productId")
    }

    //--------------------------------navigate to product fragment ---------------------------//
    private val _navigateToProduct = MutableLiveData<Int?>()
    val navigateToProduct: LiveData<Int?>
        get() = _navigateToProduct

    fun onMedicineClicked(id: Int) {
        _navigateToProduct.value = id
    }

    fun onMedicineNavigated() {
        _navigateToProduct.value = null
    }

    val medicines = listOf<Medicine>(
        Medicine(
            id = 1,
            name = "دارو 1",
            imageUri = "",
            price = 15000,
            stock = 0,
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
        ),
        Medicine(
            id = 2,
            name = "دارو 2",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png",
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
        ),
        Medicine(
            id = 3,
            name = "دارو 3",
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
        ),
        Medicine(
            id = 4,
            name = "دارو 4",
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
        ),
        Medicine(
            id = 5,
            name = "دارو 5",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png",
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
        ),
        Medicine(
            id = 6,
            name = "دارو 6",
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
        ),
        Medicine(
            id = 7,
            name = "دارو 7",
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
        ),
        Medicine(
            id = 8,
            name = "دارو 8",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png",
            price = 15000,
            stock = 10, company = "ایزان دارو",
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
        ),
        Medicine(
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
    )

}