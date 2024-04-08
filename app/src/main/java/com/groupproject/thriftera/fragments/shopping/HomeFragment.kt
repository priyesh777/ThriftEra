package com.groupproject.thriftera.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.groupproject.thriftera.R
import com.groupproject.thriftera.adapters.HomeViewpagerAdapter
import com.groupproject.thriftera.databinding.FragmentHomeBinding
import com.groupproject.thriftera.fragments.categories.*
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home) {
    //fragment used to wrap the home elements
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragments = arrayListOf(
            MainCategoryFragment(),
            CupboardFragment(),
            TableFragment(),
            AccessoryFragment(),
//            FurnitureFragment()
        )

        binding.viewpagerHome.isUserInputEnabled = false

        val viewPager2Adapter =
            HomeViewpagerAdapter(categoriesFragments, childFragmentManager, lifecycle)
        binding.viewpagerHome.adapter = viewPager2Adapter
//        This needs to be applied for category-modification
        TabLayoutMediator(binding.tabLayout, binding.viewpagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = "All"
                1 -> tab.text = "Furniture"
                2 -> tab.text = "Tables"
                3 -> tab.text = "Accessories"
//                2 -> tab.text = "Furniture"
//                3 -> tab.text = "Cupboards"
            }
        }.attach()
    }
}