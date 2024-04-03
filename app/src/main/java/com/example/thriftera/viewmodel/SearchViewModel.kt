package com.example.thriftera.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thriftera.data.Product
import com.example.thriftera.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    private val _allProducts = MutableStateFlow<Resource<List<Product>>>(Resource.Loading())

    private val _filteredProducts = MutableStateFlow<List<Product>>(listOf())
    val filteredProducts: StateFlow<List<Product>> = _filteredProducts

    private val TAG = "SearchVM"

    init {
        fetchAllProducts()
        setupSearch()
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun setupSearch() {
        viewModelScope.launch {
            _searchQuery.debounce(300) // 300ms debounce period
                .flatMapLatest { query ->
                    filterProducts(query)
                    return@flatMapLatest _filteredProducts
                }
                .collect { filteredList ->
                    _filteredProducts.value = filteredList
                }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun filterProducts(query: String?) {
        val filteredList = if (query.isNullOrEmpty()) {
            _allProducts.value.data ?: listOf()
        } else {
            _allProducts.value.data?.filter {
                it.name.lowercase(Locale.getDefault())
                    .contains(query.lowercase(Locale.getDefault()))
            } ?: listOf()
        }
        _filteredProducts.value = filteredList
    }

    private fun fetchAllProducts() {
        viewModelScope.launch {
            firestore.collection("products").get().addOnSuccessListener { result ->
                val specialProductsList = result.toObjects(Product::class.java)
                _allProducts.value = Resource.Success(specialProductsList)
                Log.i(TAG, specialProductsList.toString())
            }.addOnFailureListener { exception ->
                _allProducts.value = Resource.Error(exception.message.toString())
                Log.e(TAG, exception.message.toString())
            }
        }
    }
}