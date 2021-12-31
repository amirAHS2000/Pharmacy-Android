package com.example.pharmacyapp.ui.fragment.prepare.signup

import android.os.Bundle
import android.util.Log
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
import com.example.pharmacyapp.util.NetworkResult
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
    ): View {

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

        viewModel.signUpResponse.observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is NetworkResult.Loading -> {
                        binding.signUpProgressBar.visibility = View.VISIBLE
                        binding.signUpErrorTextView.visibility = View.GONE
                    }
                    is NetworkResult.Error -> {
                        binding.signUpProgressBar.visibility = View.GONE
                        binding.signUpErrorTextView.visibility = View.VISIBLE
                        binding.signUpErrorTextView.text = response.message
                    }
                    is NetworkResult.Success -> {
                        if (response.data?.status == true) {
                            binding.signUpProgressBar.visibility = View.VISIBLE
                            binding.signUpErrorTextView.visibility = View.GONE
                            viewModel.onSaveUser(response.data)
                        } else {
                            binding.signUpProgressBar.visibility = View.GONE
                            binding.signUpErrorTextView.visibility = View.VISIBLE
                            Log.v("server login error", response.data?.message.toString())
                            binding.signUpErrorTextView.text =
                                getString(R.string.login_credential_error)
                        }
                    }
                }
            }
        }

        viewModel.saveUserState.observe(viewLifecycleOwner) {
            it?.let { state ->
                binding.signUpProgressBar.visibility = View.VISIBLE
                if (state) {
                    viewModel.onNavigateToMain()
                }
            }
            if (it == null)
                binding.signUpProgressBar.visibility = View.GONE
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