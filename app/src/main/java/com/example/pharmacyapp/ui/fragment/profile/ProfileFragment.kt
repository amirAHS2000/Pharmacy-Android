package com.example.pharmacyapp.ui.fragment.profile

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
import com.example.pharmacyapp.databinding.FragmentProfileBinding
import com.example.pharmacyapp.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getToken()
        viewModel.getUserInformation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.sellsBox2.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_purchaseFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userInformationResponse.observe(viewLifecycleOwner, Observer {
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
                            binding.user = networkResult.data.result[0].referred
                        }
                    }
                }
            }
        })
    }
}