package com.example.pharmacyapp.ui.fragment.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.pharmacyapp.R
import com.example.pharmacyapp.databinding.FragmentProductBinding
import com.example.pharmacyapp.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMedicine()
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
//        binding.viewPager.adapter = ImageViewPagerAdapter()
//
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ ->
//        }.attach()
        //---------------------------------------------------------------------//

        binding.productToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.medicineResponse.observe(viewLifecycleOwner, Observer {
            it?.let { networkResult ->
                when (networkResult) {
                    is NetworkResult.Error -> {
                        // TODO: 1/20/2022 create a custom dialog fragment which has a progressbar
                        Toast.makeText(requireContext(), "can't load data", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is NetworkResult.Loading -> {

                    }
                    is NetworkResult.Success -> {
                        if (networkResult.data?.result == null) {
                            Toast.makeText(
                                requireContext(),
                                "no data available",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            binding.medicine = networkResult.data.result
                        }
                    }
                }
            }
        })
    }
}