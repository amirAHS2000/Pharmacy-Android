package com.example.pharmacyapp.ui.fragment.nonmedicines

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.model.NonMedicine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NonMedicineViewModel @Inject constructor(
    private val repository: Repository,
    application: Application,
    private val state: SavedStateHandle
) : AndroidViewModel(application) {


    //test
    init {
        val productId: Int? = state.get<Int>("nonMedCategoryId")
        Log.i("testArgs", "$productId")
    }

    //--------------------navigate to product----------------------//
    private val _navigateToProduct = MutableLiveData<Int?>()
    val navigateToProduct: LiveData<Int?>
        get() = _navigateToProduct

    fun onNonMedicineClicked(id: Int) {
        _navigateToProduct.value = id
    }

    fun onNonMedicineNavigated() {
        _navigateToProduct.value = null
    }

    val nonMedicines = listOf<NonMedicine>(
        NonMedicine(id = 1, name = "n1", imageUri = "", price = 15000, stock = 10),
        NonMedicine(
            id = 2,
            name = "n2",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png",
            price = 15000,
            stock = 10
        ),
        NonMedicine(id = 3, name = "n3", imageUri = "", price = 15000, stock = 10),
        NonMedicine(id = 4, name = "n4", imageUri = "", price = 15000, stock = 10),
        NonMedicine(
            id = 5,
            name = "n5",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png",
            price = 15000,
            stock = 10
        ),
        NonMedicine(id = 6, name = "n6", imageUri = "", price = 15000, stock = 10),
        NonMedicine(id = 7, name = "n7", imageUri = "", price = 15000, stock = 10),
        NonMedicine(
            id = 8,
            name = "n8",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png",
            price = 15000,
            stock = 10
        ),
        NonMedicine(id = 9, name = "n9", imageUri = "", price = 15000, stock = 10)
    )
}