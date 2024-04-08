package com.groupproject.thriftera.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.groupproject.thriftera.R
import com.groupproject.thriftera.databinding.ActivityShoppingBinding
import com.groupproject.thriftera.notifications.NotificationHelper
import com.groupproject.thriftera.util.Resource
import com.groupproject.thriftera.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingActivity : AppCompatActivity() {
    //This is the entry-point for the activities related to shopping
    val binding by lazy {
        ActivityShoppingBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var notificationHelper: NotificationHelper

    val viewModel by viewModels<CartViewModel>()


    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            Log.i("ShoppingActivity","Notification permission is granted")
        } else {
            Toast.makeText(this, "Please enable permission to receive notifications", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        requestPermissionLauncher.launch("android.permission.POST_NOTIFICATIONS")

        val navController = findNavController(R.id.shoppingHostFragment)
        binding.bottomNavigation.setupWithNavController(navController)

        lifecycleScope.launchWhenStarted {
            viewModel.cartProducts.collectLatest {
                when (it) {
                    is Resource.Success -> {
                        val count = it.data?.size ?: 0
                        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
                        bottomNavigation.getOrCreateBadge(R.id.cartFragment).apply {
                            number = count
                            backgroundColor = resources.getColor(R.color.g_blue)
                        }
                    }
                    else -> Unit
                }
            }
        }
    }
}