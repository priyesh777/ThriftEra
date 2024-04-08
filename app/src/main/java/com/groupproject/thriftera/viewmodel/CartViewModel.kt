package com.groupproject.thriftera.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.groupproject.thriftera.constants.BOOKED_ITEMS_COLLECTION
import com.groupproject.thriftera.constants.CART_COLLECTION
import com.groupproject.thriftera.constants.PRODUCTS_COLLECTION
import com.groupproject.thriftera.constants.USER_COLLECTION
import com.groupproject.thriftera.data.CartProduct
import com.groupproject.thriftera.data.CartProductNew
import com.groupproject.thriftera.data.Product
import com.groupproject.thriftera.firebase.FirebaseCommon
import com.groupproject.thriftera.helper.getProductPriceAfterDiscount
import com.groupproject.thriftera.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
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
            val unitPrice = cartProduct.product.offerPercentage?.let { offerPercentage ->
                getProductPriceAfterDiscount(cartProduct.product.price, offerPercentage)
            } ?: cartProduct.product.price

            (unitPrice * cartProduct.quantity).toDouble()
        }.toFloat()
    }

    init {
        getCartProducts()
    }


    private fun getCartProducts() {
        viewModelScope.launch {
            _cartProducts.emit(Resource.Loading())
            val userCartRef = firestore.collection(USER_COLLECTION).document(auth.uid!!)
                .collection(CART_COLLECTION)
            userCartRef.addSnapshotListener { snapshot, e ->
                if (e != null) {
                    viewModelScope.launch {
                        _cartProducts.emit(
                            Resource.Error(
                                e.message ?: "An error occurred fetching cart products"
                            )
                        )
                    }
                    return@addSnapshotListener
                }

                cartProductDocuments = snapshot?.documents ?: emptyList()

                viewModelScope.launch {
                    val cartProductsDeferred = snapshot?.documents?.mapNotNull { documentSnapshot ->
                        val cartProductNew = documentSnapshot.toObject(CartProductNew::class.java)
                        cartProductNew?.productId?.takeIf { it.isNotBlank() }?.let { productId ->
                            async {
                                try {
                                    val productSnapshot = firestore.collection(PRODUCTS_COLLECTION)
                                        .document(productId).get().await()
                                    val product = productSnapshot.toObject(Product::class.java)
                                    product?.let {
                                        CartProduct(
                                            product = it,
                                            quantity = cartProductNew.quantity,
                                            selectedColor = cartProductNew.selectedColor,
                                            selectedSize = cartProductNew.selectedSize
                                        )
                                    }
                                } catch (e: Exception) {
                                    null // Handle or log the error as appropriate
                                }
                            }
                        }
                    }?.awaitAll()?.filterNotNull() ?: emptyList()

                    _cartProducts.emit(Resource.Success(cartProductsDeferred))
                }
            }
        }
    }


    fun bookItems(products: List<CartProduct>) {
        viewModelScope.launch {
            val bookedCollection =
                firestore.collection(USER_COLLECTION).document(auth.uid!!)
                    .collection(BOOKED_ITEMS_COLLECTION)
            try {
                products.forEach {
                    bookedCollection.add(it)
                }
                _successDialog.emit(true)
                deleteAllProductsInCart()
            } catch (e: Exception) {
                e.printStackTrace()
                _successDialog.emit(true)
            }
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
        val cartItemRef = firestore.collection(USER_COLLECTION).document(auth.uid!!)
            .collection(CART_COLLECTION).document(documentId)

        firestore.runTransaction { transaction ->
            val snapshot = transaction.get(cartItemRef)
            val currentQuantity = snapshot.getLong("quantity") ?: 0L

            if (currentQuantity > 1) {
                transaction.update(cartItemRef, "quantity", FieldValue.increment(-1))
            } else if (currentQuantity == 1L) {
                transaction.delete(cartItemRef)
            } else {
            }
        }.addOnSuccessListener {
            Log.d(
                "decreaseQuantity",
                "Transaction successfully completed for document: $documentId"
            )
        }.addOnFailureListener { e ->
            Log.w("decreaseQuantity", "Transaction failed for document: $documentId", e)
            viewModelScope.launch { _cartProducts.emit(Resource.Error(e.message.toString())) }
        }
    }


    private fun increaseQuantity(documentId: String) {
        val cartItemRef = firestore.collection(USER_COLLECTION).document(auth.uid!!)
            .collection(CART_COLLECTION).document(documentId)

        cartItemRef.update("quantity", FieldValue.increment(1))
            .addOnSuccessListener {
                Log.d("increaseQuantity", "Quantity successfully increased.")
            }
            .addOnFailureListener { e ->
                Log.w("increaseQuantity", "Error increasing quantity.", e)
                viewModelScope.launch { _cartProducts.emit(Resource.Error(e.message.toString())) }
            }
    }

    fun resetSuccessDialog() {
        _successDialog.value = false
    }
}