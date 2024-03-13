package com.example.thriftera.fragments.shopping

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.thriftera.BuildConfig
import com.example.thriftera.R
import com.example.thriftera.activities.AddProductActivity
import com.example.thriftera.activities.LoginRegisterActivity
import com.example.thriftera.databinding.FragmentProfileBinding
import com.example.thriftera.util.Resource
import com.example.thriftera.util.showBottomNavigationView
import com.example.thriftera.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    val viewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.constraintProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_userAccountFragment)
        }

        binding.userBookedItems.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_fragmentUserBookedItems)
        }

        binding.linearAddProduct.setOnClickListener {
            val intent = Intent(requireActivity(), AddProductActivity::class.java)
            requireActivity().startActivity(intent)
        }

        binding.linearLogOut.setOnClickListener {
            viewModel.logout()
            val intent = Intent(requireActivity(), LoginRegisterActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.tvVersion.text = "Version ${BuildConfig.VERSION_CODE}"

        lifecycleScope.launchWhenStarted {
            viewModel.user.collectLatest { userResult ->
                when (userResult) {
                    is Resource.Loading -> {
                        binding.progressbarSettings.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressbarSettings.visibility = View.GONE
                        Glide.with(requireView()).load(userResult.data!!.imagePath).error(ColorDrawable(
                            Color.BLACK)).into(binding.imageUser)
                        val user = userResult.data
                        binding.tvUserName.text = "${user.firstName} ${user.lastName}"
                        if (user.email == "admin@gmail.com") {
                            binding.tvAdminAddProduct.visibility = View.VISIBLE
                            binding.linearAddProduct.visibility = View.VISIBLE

                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), userResult.message, Toast.LENGTH_SHORT).show()
                        binding.progressbarSettings.visibility = View.GONE
                    }
                    else -> Unit
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showBottomNavigationView()
    }
}