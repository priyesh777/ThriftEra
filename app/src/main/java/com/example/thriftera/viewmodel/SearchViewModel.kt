package com.example.thriftera.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thriftera.data.Product
import com.example.thriftera.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _allProducts = MutableStateFlow<Resource<List<Product>>>(Resource.Loading())
    val allProducts: StateFlow<Resource<List<Product>>> = _allProducts

    private val TAG = "SearchVM"

    init {
        fetchAllProducts()
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