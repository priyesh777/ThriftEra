package com.example.thriftera.fragments.loginRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.thriftera.R
import com.example.thriftera.data.User
import com.example.thriftera.databinding.FragmentRegisterBinding
import com.example.thriftera.util.RegisterFieldsState
import com.example.thriftera.util.RegisterValidation
import com.example.thriftera.util.Resource
import com.example.thriftera.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment() {
    //Fragment to handle the register user activity
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()
    private val TAG = "RegisterFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.apply {
            haveAccountAlready.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            buttonRegisterRegister.setOnClickListener {
                val user = User(
                    edFirstNameRegister.text.toString().trim(),
                    edLastNameRegister.text.toString().trim(),
                    edEmailRegister.text.toString().trim()
                )
                val password = edPasswordRegister.text.toString()
                viewModel.createAccountWithEmailAndPassword(user, password)
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.register.collect { resource ->
                handleRegistrationState(resource)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.validation.collect { validationState ->
                handleValidationState(validationState)
            }
        }
    }

    private fun handleRegistrationState(resource: Resource<User>) {
        when (resource) {
            is Resource.Loading -> {
                binding.buttonRegisterRegister.startAnimation()
            }

            is Resource.Success -> {
                binding.buttonRegisterRegister.revertAnimation()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                Toast.makeText(
                    requireContext(),
                    "Registered successfully! Please login to continue",
                    Toast.LENGTH_SHORT
                ).show()
            }

            is Resource.Error -> {
                binding.buttonRegisterRegister.revertAnimation()
                Toast.makeText(
                    requireContext(),
                    resource.message,
                    Toast.LENGTH_LONG
                ).show()
            }

            else -> Unit
        }
    }

    private fun handleValidationState(validationState: RegisterFieldsState) {
        if (validationState.email is RegisterValidation.Failed) {
            binding.edEmailRegister.apply {
                requestFocus()
                error = validationState.email.message
            }
        }

        if (validationState.password is RegisterValidation.Failed) {
            binding.edPasswordRegister.apply {
                requestFocus()
                error = validationState.password.message
            }
        }
    }
}