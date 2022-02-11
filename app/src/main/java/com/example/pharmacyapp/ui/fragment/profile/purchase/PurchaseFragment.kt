package com.example.pharmacyapp.ui.fragment.profile.purchase

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
import com.example.pharmacyapp.adapter.MedicineVerticalAdapter
import com.example.pharmacyapp.adapter.PurchaseListAdapter
import com.example.pharmacyapp.databinding.FragmentPurchasePageBinding
import com.example.pharmacyapp.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PurchaseFragment : Fragment() {

    private lateinit var binding: FragmentPurchasePageBinding
    private val viewModel: PurchaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getToken()
        viewModel.getProduct()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_purchase_page, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.purchaseList.adapter = PurchaseListAdapter()

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.productPurchaseResponse.observe(viewLifecycleOwner, Observer {
            it?.let { networkResult ->
                when (networkResult) {
                    is NetworkResult.Loading -> {
//                        binding.medicineListPlaceholder.visibility = View.VISIBLE
                    }
                    is NetworkResult.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "${networkResult.message}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        // TODO: 1/20/2022 show a message "can't load data"
//                        binding.medicineListPlaceholder.visibility = View.VISIBLE
                    }
                    is NetworkResult.Success -> {
                        if (networkResult.data?.result == null) {
                            // TODO: 1/20/2022 there is no data for showing
//                            binding.medicineListPlaceholder.visibility = View.VISIBLE
                        } else {
//                            binding.medicineListPlaceholder.visibility = View.GONE
//                            binding.medicinesList.visibility = View.VISIBLE
                            val adapter = binding.purchaseList.adapter as PurchaseListAdapter
                            adapter.submitList(networkResult.data.result)
                        }
                    }
                }
            }
        })
    }
}