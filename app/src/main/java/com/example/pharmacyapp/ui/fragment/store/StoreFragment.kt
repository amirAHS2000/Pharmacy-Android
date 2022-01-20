package com.example.pharmacyapp.ui.fragment.store

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.pharmacyapp.R
import com.example.pharmacyapp.adapter.*
import com.example.pharmacyapp.databinding.FragmentStoreBinding
import com.example.pharmacyapp.util.NetworkResult
import com.example.pharmacyapp.util.clicklistener.CategoryListener
import com.example.pharmacyapp.util.clicklistener.MedicineListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentStoreBinding

    private val viewModel: StoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getCategories()
        viewModel.getMedicineTopSellers()
        viewModel.getNonMedicineTopSellers()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.categoriesList.adapter = CategoryAdapter(CategoryListener { categoryId ->
            viewModel.onMedicineItemClicked(categoryId)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.categories.observe(viewLifecycleOwner, Observer {
            it?.let { networkResult ->
                when (networkResult) {
                    is NetworkResult.Loading -> {
                        binding.categoryListPlaceholder.visibility = View.VISIBLE
                    }
                    is NetworkResult.Error -> {
                        Toast.makeText(requireContext(), "can't load data", Toast.LENGTH_SHORT)
                            .show()
                        // TODO: 1/20/2022 show a message "can't load data"
                        binding.categoryListPlaceholder.visibility = View.VISIBLE
                    }
                    is NetworkResult.Success -> {
                        if (networkResult.data?.result == null) {
                            // TODO: 1/20/2022 there is no data for showing
                            binding.categoryListPlaceholder.visibility = View.VISIBLE
                        } else {
                            binding.categoryListPlaceholder.visibility = View.GONE
                            binding.categoriesList.visibility = View.VISIBLE
                            val adapter = binding.categoriesList.adapter as CategoryAdapter
                            adapter.submitList(networkResult.data.result)
                            Log.i("testCategory", "${networkResult.data.result.size}")
                        }
                    }
                }
            }
        })
        viewModel.medicineTopSellers.observe(viewLifecycleOwner, Observer {
            it?.let { networkResult ->
                when (networkResult) {
                    is NetworkResult.Loading -> {
                        binding.medicineListPlaceholder.visibility = View.VISIBLE
                    }
                    is NetworkResult.Error -> {
                        Toast.makeText(requireContext(), "can't load data", Toast.LENGTH_SHORT)
                            .show()
                        // TODO: 1/20/2022 show a message "can't load data"
                        binding.medicineListPlaceholder.visibility = View.VISIBLE
                    }
                    is NetworkResult.Success -> {
                        if (networkResult.data?.result == null) {
                            // TODO: 1/20/2022 there is no data for showing
                            binding.medicineListPlaceholder.visibility = View.VISIBLE
                        } else {
                            binding.medicineListPlaceholder.visibility = View.GONE
                            binding.medicineTopSellersList.visibility = View.VISIBLE
                            val adapter = binding.medicineTopSellersList.adapter as MedicineAdapter
                            adapter.submitList(networkResult.data.result)
                        }
                    }
                }
            }
        })
        viewModel.nonMedicineTopSellers.observe(viewLifecycleOwner, Observer {
            it?.let { networkResult ->
                when (networkResult) {
                    is NetworkResult.Loading -> {
                        binding.nonMedicineListPlaceholder.visibility = View.VISIBLE
                    }
                    is NetworkResult.Error -> {
                        Toast.makeText(requireContext(), "can't load data", Toast.LENGTH_SHORT)
                            .show()
                        // TODO: 1/20/2022 show a message "can't load data"
                        binding.nonMedicineListPlaceholder.visibility = View.VISIBLE
                    }
                    is NetworkResult.Success -> {
                        if (networkResult.data?.result == null) {
                            // TODO: 1/20/2022 there is no data for showing
                            binding.nonMedicineListPlaceholder.visibility = View.VISIBLE
                        } else {
                            binding.nonMedicineListPlaceholder.visibility = View.GONE
                            binding.nonMedsTopSellersList.visibility = View.VISIBLE
                            val adapter = binding.nonMedsTopSellersList.adapter as MedicineAdapter
                            adapter.submitList(networkResult.data.result)
                        }
                    }
                }
            }
        })
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