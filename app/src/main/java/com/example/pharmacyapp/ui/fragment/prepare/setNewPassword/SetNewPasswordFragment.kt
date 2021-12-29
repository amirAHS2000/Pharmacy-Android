package com.example.pharmacyapp.ui.fragment.prepare.setNewPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pharmacyapp.R
import com.example.pharmacyapp.databinding.SetNewPasswordFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetNewPasswordFragment : Fragment() {

    private lateinit var viewModel: SetNewPasswordViewModel
    private lateinit var binding: SetNewPasswordFragmentBinding
    private val args: SetNewPasswordFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SetNewPasswordViewModel::class.java]
        viewModel.setUser(args.user)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.set_new_password_fragment, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            var isNotEmpty = true
            var isPasswordValid = true

            binding.passwordInputLayoutSetNewPassword.apply {
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

            binding.confirmInputLayoutSetNewPassword.apply {
                when {
                    editText?.text?.isEmpty() == true -> {
                        isErrorEnabled = true
                        error = resources.getString(R.string.confirm_name_empty_error)
                        isNotEmpty = false
                    }
                    editText?.text.toString() != binding.passwordInputLayoutSetNewPassword.editText?.text.toString() -> {
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
                val password = binding.passwordInputLayoutSetNewPassword.editText?.text.toString()
                viewModel.resetPassword(password)
                viewModel.onNavigateToLogin() // TODO move to a better place after implementing resetPassword
            }
        }

        viewModel.navigateToLogin.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(SetNewPasswordFragmentDirections.actionSetNewPasswordFragmentToLoginFragment2())
            }
        }
    }
}