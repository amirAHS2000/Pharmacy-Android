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
import com.example.pharmacyapp.util.clicklistener.MedicineListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedicineFragment : Fragment() {

    private lateinit var binding: FragmentMedicinesBinding
//    private lateinit var viewModel: MedicineViewModel

    private val viewModel : MedicineViewModel by viewModels()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        viewModel = ViewModelProvider(requireActivity())[MedicineViewModel::class.java]
//    }

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

        viewModel.navigateToProduct.observe(viewLifecycleOwner, Observer { medicineId ->
            medicineId?.let {
                findNavController().navigate(
                    MedicineFragmentDirections.actionMedicineFragmentToProductFragment(medicineId)
                )
                viewModel.onMedicineNavigated()
            }
        })

        return binding.root
    }
}