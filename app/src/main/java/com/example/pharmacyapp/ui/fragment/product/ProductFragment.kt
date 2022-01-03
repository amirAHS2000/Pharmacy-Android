package com.example.pharmacyapp.ui.fragment.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pharmacyapp.R
import com.example.pharmacyapp.adapter.ImageViewPagerAdapter
import com.example.pharmacyapp.databinding.FragmentProductBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //---------------------medicine images (viewpager)---------------------//
        binding.viewPager.adapter = ImageViewPagerAdapter()

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ ->
        }.attach()
        //---------------------------------------------------------------------//

        return binding.root
    }
}