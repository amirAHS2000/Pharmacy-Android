package com.example.pharmacyapp.ui.fragment.prepare.setNewPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pharmacyapp.R
import com.example.pharmacyapp.databinding.SetNewPasswordFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetNewPasswordFragment : Fragment() {

    private lateinit var viewModel: SetNewPasswordViewModel
    private lateinit var binding: SetNewPasswordFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SetNewPasswordViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.set_new_password_fragment, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }



}