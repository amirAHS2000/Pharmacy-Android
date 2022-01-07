package com.example.pharmacyapp.ui.fragment.medicines

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.model.Medicine
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
        Medicine(id = 1, name = "t1", imageUri = "", price = 15000, stock = 10),
        Medicine(
            id = 2,
            name = "t2",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png",
            price = 15000,
            stock = 10
        ),
        Medicine(id = 3, name = "t3", imageUri = "", price = 15000, stock = 10),
        Medicine(id = 4, name = "t4", imageUri = "", price = 15000, stock = 10),
        Medicine(
            id = 5,
            name = "t5",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png",
            price = 15000,
            stock = 10
        ),
        Medicine(id = 6, name = "t6", imageUri = "", price = 15000, stock = 10),
        Medicine(id = 7, name = "t7", imageUri = "", price = 15000, stock = 10),
        Medicine(
            id = 8,
            name = "t8",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png",
            price = 15000,
            stock = 10
        ),
        Medicine(id = 9, name = "t9", imageUri = "", price = 15000, stock = 10)
    )

}