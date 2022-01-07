package com.example.pharmacyapp.ui.fragment.store

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pharmacyapp.data.Repository
import com.example.pharmacyapp.model.Category
import com.example.pharmacyapp.model.Medicine
import com.example.pharmacyapp.model.NonMedicine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    val repository: Repository,
    application: Application,
) : AndroidViewModel(application) {

    //-------------------------navigate to medicines fragment----------------------//
    private val _navigateToMedicine = MutableLiveData<Int?>()
    val navigateToMedicine: LiveData<Int?>
        get() = _navigateToMedicine

    fun onMedicineItemClicked(id: Int) {
        _navigateToMedicine.value = id
    }

    fun onMedicineNavigated() {
        _navigateToMedicine.value = null
    }

    //-----------------------navigate to non-medicines fragment--------------------//
    private val _navigateToNonMedicine = MutableLiveData<Int?>()
    val navigateToNonMedicine: LiveData<Int?>
        get() = _navigateToNonMedicine

    fun onNonMedicineClicked(id: Int) {
        _navigateToNonMedicine.value = id
    }

    fun onNonMedicineNavigated() {
        _navigateToNonMedicine.value = null
    }

    //---------------------navigate to product (detail) fragment-------------------//
    private val _navigateToProduct = MutableLiveData<Int?>()
    val navigateToProduct: LiveData<Int?>
        get() = _navigateToProduct

    fun onProductClicked(id: Int) {
        _navigateToProduct.value = id
    }

    fun onProductNavigated() {
        _navigateToProduct.value = null
    }

    val medicineCategory = listOf<Category>(
        Category(
            id = 1,
            name = "c1",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png"
        ),
        Category(id = 2, name = "c2", imageUri = ""),
        Category(id = 3, name = "c3", imageUri = ""),
        Category(
            id = 4,
            name = "c4",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png"
        ),
        Category(id = 5, name = "c5", imageUri = ""),
        Category(id = 6, name = "c6", imageUri = ""),
        Category(id = 7, name = "c7", imageUri = ""),
        Category(
            id = 8,
            name = "c8",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png"
        ),
        Category(id = 9, name = "c9", imageUri = "")
    )

    val nonMedicineCategory = listOf<Category>(
        Category(
            id = 1,
            name = "c1",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png"
        ),
        Category(id = 2, name = "c2", imageUri = ""),
        Category(id = 3, name = "c3", imageUri = ""),
        Category(
            id = 4,
            name = "c4",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png"
        ),
        Category(id = 5, name = "c5", imageUri = ""),
        Category(id = 6, name = "c6", imageUri = ""),
        Category(id = 7, name = "c7", imageUri = ""),
        Category(
            id = 8,
            name = "c8",
            imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/1200px-Android_logo_2019_%28stacked%29.svg.png"
        ),
        Category(id = 9, name = "c9", imageUri = "")
    )

    val medicineTopSellers = listOf<Medicine>(
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

    val nonMedicineTopSellers = listOf<NonMedicine>(
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