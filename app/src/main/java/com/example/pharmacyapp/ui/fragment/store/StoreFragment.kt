package com.example.pharmacyapp.ui.fragment.store

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pharmacyapp.R
import com.example.pharmacyapp.adapter.*
import com.example.pharmacyapp.databinding.FragmentStoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentStoreBinding
    private lateinit var viewModel: StoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[StoreViewModel::class.java]
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
            Toast.makeText(context, "$categoryId", Toast.LENGTH_SHORT).show()
        })

        binding.topSellersList.adapter = MedicineAdapter(MedicineListener { medicineId ->
            Toast.makeText(context, "$medicineId", Toast.LENGTH_SHORT).show()
        })

        binding.nonMedsList.adapter = NonMedicineAdapter(NonMedicineCLickListener { nonMedicineId ->
            Toast.makeText(context, "$nonMedicineId", Toast.LENGTH_SHORT).show()
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