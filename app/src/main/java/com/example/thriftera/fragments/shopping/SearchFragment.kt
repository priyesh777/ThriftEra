package com.example.thriftera.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thriftera.R
import com.example.thriftera.adapters.SearchAdapter
import com.example.thriftera.databinding.FragmentSearchBinding
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

        // observer the products
        observeProducts()

        // initialize search view
        initSearchView()
    }

    private fun initSearchView() {
        binding.svProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.setSearchQuery(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return if (!newText.isNullOrEmpty()) {
                    viewModel.setSearchQuery(newText)
                    binding.rvSearch.visibility = View.VISIBLE
                    binding.layoutSearchEmpty.visibility = View.GONE
                    true
                } else {
                    with(binding) {
                        rvSearch.visibility = View.GONE
                        layoutSearchEmpty.visibility = View.VISIBLE
                    }
                    false
                }
            }
        })

    }

    private fun observeProducts() {
        lifecycleScope.launch {
            viewModel.filteredProducts.collectLatest { products ->
                searchAdapter.submitList(products)
            }
        }
    }

    private fun initSearchAdapter() {
        searchAdapter = SearchAdapter()
        binding.rvSearch.layoutManager = LinearLayoutManager(context)
        binding.rvSearch.adapter = searchAdapter

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
}