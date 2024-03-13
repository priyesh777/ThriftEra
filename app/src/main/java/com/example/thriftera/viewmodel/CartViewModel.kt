package com.example.thriftera.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thriftera.constants.BOOKED_ITEMS_COLLECTION
import com.example.thriftera.constants.CART_COLLECTION
import com.example.thriftera.constants.USER_COLLECTION
import com.example.thriftera.data.CartProduct
import com.example.thriftera.data.Product
import com.example.thriftera.firebase.FirebaseCommon
import com.example.thriftera.helper.getProductPrice
import com.example.thriftera.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val firebaseCommon: FirebaseCommon
) : ViewModel() {

    private val _cartProducts =
        MutableStateFlow<Resource<List<CartProduct>>>(Resource.Unspecified())
    val cartProducts = _cartProducts.asStateFlow()

    val productsPrice = cartProducts.map {
        when (it) {
            is Resource.Success -> {
                calculatePrice(it.data!!)
            }

            else -> null
        }
    }
    private val _deleteDialog = MutableSharedFlow<CartProduct>()
    val deleteDialog = _deleteDialog.asSharedFlow()

    private val _successDialog = MutableStateFlow(false)
    val successDialog = _successDialog.asStateFlow()

    fun updateSuccessDialog(status: Boolean) {
        _successDialog.value = status
    }

    private var cartProductDocuments = emptyList<DocumentSnapshot>()


    fun deleteCartProduct(cartProduct: CartProduct) {
        val index = cartProducts.value.data?.indexOf(cartProduct)
        if (index != null && index != -1) {
            val documentId = cartProductDocuments[index].id
            firestore.collection("user").document(auth.uid!!).collection("cart")
                .document(documentId).delete()
        }
    }


    private fun calculatePrice(data: List<CartProduct>): Float {
        return data.sumOf { cartProduct ->
            (cartProduct.product.offerPercentage.getProductPrice(cartProduct.product.price) *
                    cartProduct.quantity).toDouble()
        }.toFloat()
    }


    init {
        getCartProducts()
    }


    private fun getCartProducts() {
        viewModelScope.launch { _cartProducts.emit(Resource.Loading()) }
        firestore.collection(USER_COLLECTION).document(auth.uid!!).collection(CART_COLLECTION)
            .addSnapshotListener { value, error ->
                if (error != null || value == null) {
                    viewModelScope.launch { _cartProducts.emit(Resource.Error(error?.message.toString())) }
                } else {
                    cartProductDocuments = value.documents
                    val cartProducts = value.toObjects(CartProduct::class.java)
                    viewModelScope.launch { _cartProducts.emit(Resource.Success(cartProducts)) }
                }
            }
    }

    fun bookItems(products: List<CartProduct>) {
        val bookedCollection =
            firestore.collection(USER_COLLECTION).document(auth.uid!!)
                .collection(BOOKED_ITEMS_COLLECTION)
        try {
            products.forEach {
                bookedCollection.add(it)
            }
            _successDialog.value = true
            //delete documents in cart
            deleteAllProductsInCart()
        } catch (e: Exception) {
            e.printStackTrace()
            _successDialog.value = false
        }
    }

    private fun deleteAllProductsInCart() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cartRef = firestore.collection(USER_COLLECTION).document(auth.uid!!).collection(
                    CART_COLLECTION
                )
                val documents = cartRef.get().await()

                // Delete each document
                documents.forEach { document ->
                    cartRef.document(document.id).delete().await()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun changeQuantity(
        cartProduct: CartProduct,
        quantityChanging: FirebaseCommon.QuantityChanging
    ) {

        val index = cartProducts.value.data?.indexOf(cartProduct)

        /**
         * index could be equal to -1 if the function [getCartProducts] delays which will also delay the result we expect to be inside the [_cartProducts]
         * and to prevent the app from crashing we make a check
         */
        if (index != null && index != -1) {
            val documentId = cartProductDocuments[index].id
            when (quantityChanging) {
                FirebaseCommon.QuantityChanging.INCREASE -> {
                    viewModelScope.launch { _cartProducts.emit(Resource.Loading()) }
                    increaseQuantity(documentId)
                }

                FirebaseCommon.QuantityChanging.DECREASE -> {
                    if (cartProduct.quantity == 1) {
                        viewModelScope.launch { _deleteDialog.emit(cartProduct) }
                        return
                    }
                    viewModelScope.launch { _cartProducts.emit(Resource.Loading()) }
                    decreaseQuantity(documentId)
                }
            }
        }
    }

    private fun decreaseQuantity(documentId: String) {
        firebaseCommon.decreaseQuantity(documentId) { result, exception ->
            if (exception != null)
                viewModelScope.launch { _cartProducts.emit(Resource.Error(exception.message.toString())) }
        }
    }

    private fun increaseQuantity(documentId: String) {
        firebaseCommon.increaseQuantity(documentId) { result, exception ->
            if (exception != null)
                viewModelScope.launch { _cartProducts.emit(Resource.Error(exception.message.toString())) }
        }
    }
}