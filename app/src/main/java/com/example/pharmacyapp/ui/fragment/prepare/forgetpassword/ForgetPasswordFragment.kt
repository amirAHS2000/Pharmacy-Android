package com.example.pharmacyapp.ui.fragment.prepare.forgetpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pharmacyapp.R
import com.example.pharmacyapp.databinding.FragmentForgetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgetPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgetPasswordBinding
    private lateinit var viewModel: ForgetPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ForgetPasswordViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_forget_password, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phoneInputLayoutForgotPassword.apply {
            editText?.doOnTextChanged { text, _, _, _ ->
                if (text?.isEmpty() != true && error == getString(R.string.phone_empty_error)) {
                    isErrorEnabled = false
                    error = ""
                }
            }
        }

        binding.confirmButton.setOnClickListener {
            binding.phoneInputLayoutForgotPassword.apply {
                if (editText?.text?.isEmpty() == true) {
                    isErrorEnabled = true
                    error = getString(R.string.phone_empty_error)
                } else {
                    val phone = editText!!.text.toString()
                    val user = viewModel.onFindUser(phone)
                    if (user) {
                        viewModel.onNavigateToSetNewPassword()
                    } else {
                        isErrorEnabled = true
                        error = getString(R.string.no_user_founded)
                    }
                }
            }
        }

        viewModel.navigateToSetNewPassword.observe(viewLifecycleOwner) {
            it?.let {
                //findNavController().navigate(R.id.action_forgetPasswordFragment_to_loginFragment)
                findNavController().navigate(R.id.action_forgetPasswordFragment_to_setNewPasswordFragment)
                viewModel.onNavigateToSetNewPasswordDone()
            }
        }
    }
}