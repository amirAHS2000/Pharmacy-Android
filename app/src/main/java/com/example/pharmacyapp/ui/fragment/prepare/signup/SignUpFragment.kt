package com.example.pharmacyapp.ui.fragment.prepare.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pharmacyapp.R
import com.example.pharmacyapp.databinding.FragmentSignupBinding
import com.example.pharmacyapp.ui.fragment.prepare.PrepareActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SignUpViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener {
            var isNotEmpty = true
            var isPasswordValid = true

            binding.firstNameInputLayout.apply {
                if (editText?.text?.isEmpty() == true) {
                    isErrorEnabled = true
                    error = resources.getString(R.string.firstname_empty_error)
                    isNotEmpty = false
                } else {
                    isErrorEnabled = false
                }
            }

            binding.lastNameInputLayout.apply {
                if (editText?.text?.isEmpty() == true) {
                    isErrorEnabled = true
                    error = resources.getString(R.string.lastname_empty_error)
                    isNotEmpty = false
                } else {
                    isErrorEnabled = false
                }
            }

            binding.nationalNumberInputLayout.apply {
                if (editText?.text?.isEmpty() == true) {
                    isErrorEnabled = true
                    error = resources.getString(R.string.national_name_empty_error)
                    isNotEmpty = false
                } else {
                    isErrorEnabled = false
                }
            }

            binding.phoneNumberInputLayout.apply {
                if (editText?.text?.isEmpty() == true) {
                    isErrorEnabled = true
                    error = resources.getString(R.string.phone_empty_error)
                    isNotEmpty = false
                } else {
                    isErrorEnabled = false
                }
            }

            binding.passwordInputLayoutSignUp.apply {
                when {
                    editText?.text?.isEmpty() == true -> {
                        isErrorEnabled = true
                        error = resources.getString(R.string.add_password_empty_error)
                        isNotEmpty = false
                    }
                    editText?.text?.length!! < 8 -> {
                        isErrorEnabled = true
                        error = resources.getString(R.string.password_length_error)
                        isNotEmpty = false
                    }
                    else -> isErrorEnabled = false

                }
            }

            binding.confirmInputLayout.apply {
                when {
                    editText?.text?.isEmpty() == true -> {
                        isErrorEnabled = true
                        error = resources.getString(R.string.confirm_name_empty_error)
                        isNotEmpty = false
                    }
                    editText?.text.toString() != binding.passwordInputLayoutSignUp.editText?.text.toString() -> {
                        isErrorEnabled = true
                        error = resources.getString(R.string.password_match_error)
                        isPasswordValid = false
                    }
                    else -> {
                        isErrorEnabled = false
                    }
                }
            }


            if (isNotEmpty && isPasswordValid) {
                val firstname = binding.firstNameInputLayout.editText?.text.toString()
                val lastname = binding.lastNameInputLayout.editText?.text.toString()
                val nationalNumber = binding.nationalNumberInputLayout.editText?.text.toString()
                val phone = binding.phoneNumberInputLayout.editText?.text.toString()
                val password = binding.passwordInputLayoutSignUp.editText?.text.toString()
                viewModel.onSignUp(firstname, lastname, nationalNumber, phone, password)
            }
        }

        viewModel.navigateToLogin.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                viewModel.onNavigateToLoginDone()
            }
        }

        viewModel.navigateToMain.observe(viewLifecycleOwner) {
            it?.let {
                (activity as PrepareActivity).navigateToAnotherActivity()
                viewModel.onNavigateToMainDone()
            }
        }
    }
}