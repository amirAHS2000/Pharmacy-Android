package com.example.pharmacyapp.ui.fragment.prepare.forgetpassword

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
import com.example.pharmacyapp.databinding.FragmentForgetPasswordBinding
import com.example.pharmacyapp.util.NetworkResult
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
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_forget_password, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.confirmButton.setOnClickListener {
            binding.phoneInputLayoutForgotPassword.apply {
                if (editText?.text?.isEmpty() == true) {
                    isErrorEnabled = true
                    error = getString(R.string.phone_empty_error)
                } else {
                    val phone = editText!!.text.toString()
                    viewModel.onFindUser(phone)
                }
            }
        }

        viewModel.user.observe(viewLifecycleOwner) {
            it?.let { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        binding.forgetPasswordProgressBar.visibility = View.VISIBLE
                        binding.forgetPasswordErrorTextView.visibility = View.GONE
                    }
                    is NetworkResult.Error -> {
                        binding.forgetPasswordProgressBar.visibility = View.GONE
                        binding.forgetPasswordErrorTextView.visibility = View.VISIBLE
                        binding.forgetPasswordErrorTextView.text = result.message
                    }
                    is NetworkResult.Success -> {
                        binding.forgetPasswordProgressBar.visibility = View.GONE
                        if (result.data?.status == true) {
                            binding.forgetPasswordErrorTextView.visibility = View.GONE
                            viewModel.onNavigateToSetNewPassword()
                        } else {
                            binding.forgetPasswordErrorTextView.visibility = View.VISIBLE
                            Log.v("server ForgetPassword error", result.data?.message.toString())
                            binding.forgetPasswordErrorTextView.text = getString(R.string.no_user_founded)
                        }
                    }
                }
            }
//            binding.forgetPasswordProgressBar.visibility = View.GONE
//            binding.forgetPasswordErrorTextView.visibility = View.GONE
        }

        viewModel.navigateToSetNewPassword.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(R.id.action_forgetPasswordFragment_to_setNewPasswordFragment)
                viewModel.onNavigateToSetNewPasswordDone()
            }
        }
    }
}