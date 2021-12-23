package com.example.pharmacyapp.ui.fragment.prepare.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pharmacyapp.R
import com.example.pharmacyapp.databinding.FragmentLoginBinding
import com.example.pharmacyapp.ui.fragment.prepare.PrepareActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.phoneInputLayout.also { textInput ->
            textInput.editText?.doOnTextChanged { text, _, _, _ ->
                if (text?.isNotEmpty() == true && textInput.isErrorEnabled) {
                    textInput.isErrorEnabled = false
                    textInput.error = ""
                }
            }
        }
        binding.passwordInputLayout.also { textInput ->
            textInput.editText?.doOnTextChanged { text, _, _, _ ->
                if (text?.isNotEmpty() == true && textInput.isErrorEnabled) {
                    textInput.isErrorEnabled = false
                    textInput.error = ""
                }
            }
        }

        binding.loginButton.setOnClickListener {
            var isNotEmpty = true
            if (binding.phoneInputLayout.editText?.text?.isEmpty() == true) {
                binding.phoneInputLayout.isErrorEnabled = true
                binding.phoneInputLayout.error = resources.getString(R.string.phone_empty_error)
                isNotEmpty = false
            }
            if (binding.passwordInputLayout.editText?.text?.isEmpty() == true) {
                binding.passwordInputLayout.isErrorEnabled = true
                binding.passwordInputLayout.error =
                    resources.getString(R.string.password_empty_error)
                isNotEmpty = false
            }
            if (isNotEmpty) {
                val phone = binding.phoneInputLayout.editText?.text.toString()
                val password = binding.passwordInputLayout.editText?.text.toString()
                viewModel.onLogin(phone, password)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateToMain.observe(viewLifecycleOwner) {
            it?.let {
                (activity as PrepareActivity).navigateToAnotherActivity()
                viewModel.onNavigateToMainDone()
            }
        }

        viewModel.navigateToForgotPassword.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
                viewModel.onNavigateToForgetPasswordDone()
            }
        }
        viewModel.navigateToSignUp.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
                viewModel.onNavigateToSignUpDone()
            }
        }

        viewModel.loginResponse.observe(viewLifecycleOwner) {
            it?.let { response ->
                if (response.status){
                    viewModel.onNavigateToMain()
                } else {
                    Log.d("login", response.message.toString())
                }
            }
        }
    }
}