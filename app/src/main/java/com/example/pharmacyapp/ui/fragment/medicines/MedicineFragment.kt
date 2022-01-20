package com.example.pharmacyapp.ui.fragment.medicines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pharmacyapp.R
import com.example.pharmacyapp.adapter.MedicineAdapter
import com.example.pharmacyapp.adapter.MedicineVerticalAdapter
import com.example.pharmacyapp.databinding.FragmentMedicinesBinding
import com.example.pharmacyapp.util.NetworkResult
import com.example.pharmacyapp.util.clicklistener.MedicineListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedicineFragment : Fragment() {

    private lateinit var binding: FragmentMedicinesBinding

    private val viewModel: MedicineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMedicine()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_medicines, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.medicinesList.adapter = MedicineVerticalAdapter(MedicineListener { medicineId ->
            viewModel.onMedicineClicked(medicineId)
        })

        viewModel.navigateToProduct.observe(viewLifecycleOwner, Observer {
            it?.let { medicineId ->
                findNavController().navigate(
                    MedicineFragmentDirections.actionMedicineFragmentToProductFragment(medicineId)
                )
                viewModel.onMedicineNavigated()
            }
        })

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.medicineResponse.observe(viewLifecycleOwner, Observer {
            it?.let { networkResult ->
                when (networkResult) {
                    is NetworkResult.Loading -> {
                        binding.medicineListPlaceholder.visibility = View.VISIBLE
                    }
                    is NetworkResult.Error -> {
                        Toast.makeText(requireContext(), "${networkResult.message}", Toast.LENGTH_SHORT)
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
                            binding.medicinesList.visibility = View.VISIBLE
                            val adapter = binding.medicinesList.adapter as MedicineVerticalAdapter
                            adapter.submitList(networkResult.data.result)
                        }
                    }
                }
            }
        })
    }
}