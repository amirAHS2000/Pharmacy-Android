package com.example.pharmacyapp.ui.fragment.store

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.pharmacyapp.R
import com.example.pharmacyapp.adapter.*
import com.example.pharmacyapp.databinding.FragmentStoreBinding
import com.example.pharmacyapp.util.clicklistener.CategoryListener
import com.example.pharmacyapp.util.clicklistener.MedicineListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentStoreBinding
//    private lateinit var viewModel: StoreViewModel

    private val viewModel: StoreViewModel by viewModels()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        viewModel = ViewModelProvider(requireActivity())[StoreViewModel::class.java]
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.medicineCategoriesList.adapter = CategoryAdapter(CategoryListener { categoryId ->
            viewModel.onMedicineItemClicked(categoryId)
        })

        binding.nonMedsCategoryList.adapter = CategoryAdapter(CategoryListener { categoryID ->
            viewModel.onNonMedicineClicked(categoryID)
        })

        binding.medicineTopSellersList.adapter = MedicineAdapter(MedicineListener { medicineId ->
            viewModel.onProductClicked(medicineId)
        })

        binding.nonMedsTopSellersList.adapter =
            MedicineAdapter(MedicineListener { nonMedicineId ->
                viewModel.onProductClicked(nonMedicineId)
            })

        viewModel.navigateToProduct.observe(viewLifecycleOwner, Observer { productId ->
            productId?.let {
                findNavController().navigate(
                    StoreFragmentDirections
                        .actionStoreFragmentToProductFragment(productId)
                )
                viewModel.onProductNavigated()
            }
        })

        viewModel.navigateToMedicine.observe(viewLifecycleOwner, Observer { categoryId ->
            categoryId?.let {
                findNavController().navigate(
                    StoreFragmentDirections.actionStoreFragmentToMedicineFragment(categoryId)
                )
                viewModel.onMedicineNavigated()
            }
        })

        viewModel.navigateToNonMedicine.observe(viewLifecycleOwner, Observer { categoryId ->
            categoryId?.let {
                findNavController().navigate(
                    StoreFragmentDirections.actionStoreFragmentToMedicineFragment(categoryId)
                )
                viewModel.onNonMedicineNavigated()
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.home_menu, menu)
        val search = menu.findItem(R.id.search_item)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            doingSearch(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            doingSearch(newText)
        }
        return true
    }

    private fun doingSearch(query: String) {
        // TODO: 12/22/2021 call function from viewModel that it's do search
    }
}