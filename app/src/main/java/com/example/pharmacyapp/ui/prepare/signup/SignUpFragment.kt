package com.example.pharmacyapp.ui.prepare.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pharmacyapp.R
import com.example.pharmacyapp.databinding.FragmentSignupBinding
import com.example.pharmacyapp.ui.prepare.PrepareActivity

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    private val viewModel: SignUpViewModel by lazy {
        ViewModelProvider(
            this,
            SignUpViewModelFactory(requireNotNull(this.activity).application)
        )[SignUpViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.signupBtn.setOnClickListener {
            (activity as PrepareActivity).navigateToAnotherActivity()
        }

        return binding.root
    }
}