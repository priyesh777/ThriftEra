package com.example.thriftera.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thriftera.R
import com.example.thriftera.adapters.SearchAdapter
import com.example.thriftera.databinding.FragmentSearchBinding
import com.example.thriftera.util.Resource
import com.example.thriftera.util.WrapContentLinearLayoutManager
import com.example.thriftera.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setup search adapter
        initSearchAdapter()

        // fetch all products from the database
        fetchAllProducts()

        // initialize search view
        initSearchView()
    }

    private fun initSearchView() {
        binding.svProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    searchAdapter.filter(newText)
                    binding.rvSearch.visibility = View.VISIBLE
                    binding.layoutSearchEmpty.visibility = View.GONE
                } else {
                    with(binding) {
                        rvSearch.visibility = View.GONE
                        layoutSearchEmpty.visibility = View.VISIBLE
                    }
                }
                return true
            }
        })
    }

    private fun fetchAllProducts() {
        lifecycleScope.launch {
            viewModel.allProducts.collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        toggleProgress(View.GONE)
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Loading -> {
                        toggleProgress(View.VISIBLE)
                    }

                    is Resource.Success -> {
                        val products = result.data
                        if (products != null) {
                            searchAdapter.submitList(products)
                            toggleProgress(View.GONE)
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    private fun initSearchAdapter() {
        searchAdapter = SearchAdapter()
        binding.rvSearch.apply {
            layoutManager =
                WrapContentLinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = searchAdapter
        }

        searchAdapter.onProductClick = { product ->
            val b = Bundle().apply {
                putParcelable("product", product)
            }
            findNavController().navigate(
                R.id.action_searchFragment_to_productDetailsFragment,
                b
            )
        }
    }


    private fun toggleProgress(visibility: Int) {
        binding.progressbarSearch.visibility = visibility
    }
}