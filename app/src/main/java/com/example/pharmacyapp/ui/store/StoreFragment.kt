package com.example.pharmacyapp.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pharmacyapp.R
import com.example.pharmacyapp.databinding.FragmentStoreBinding

class StoreFragment : Fragment() {

    private lateinit var binding: FragmentStoreBinding

    private val viewModel: StoreViewModel by lazy {
        ViewModelProvider(
            this,
            StoreViewModelFactory(requireNotNull(this.activity).application)
        )[StoreViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}