package com.example.pharmacyapp.ui.fragment.nonmedicines

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
import com.example.pharmacyapp.R
import com.example.pharmacyapp.adapter.NonMedicineVerticalAdapter
import com.example.pharmacyapp.databinding.FragmentNonMedicinesBinding
import com.example.pharmacyapp.util.clicklistener.NonMedicineListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NonMedicineFragment : Fragment() {

    private lateinit var binding: FragmentNonMedicinesBinding
//    private lateinit var viewModel: NonMedicineViewModel

    private val viewModel: NonMedicineViewModel by viewModels()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        viewModel = ViewModelProvider(requireActivity())[NonMedicineViewModel::class.java]
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_non_medicines, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.nonMedicineList.adapter =
            NonMedicineVerticalAdapter(NonMedicineListener { nonMedicineId ->
                viewModel.onNonMedicineClicked(nonMedicineId)
            })


        viewModel.navigateToProduct.observe(viewLifecycleOwner, Observer { nonMedicineId ->
            nonMedicineId?.let {
                findNavController().navigate(
                    NonMedicineFragmentDirections.actionNonMedicineFragmentToProductFragment(
                        nonMedicineId
                    )
                )
                viewModel.onNonMedicineNavigated()
            }
        })

        return binding.root
    }
}