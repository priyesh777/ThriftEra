package com.groupproject.thriftera.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupproject.thriftera.constants.CART_COLLECTION
import com.groupproject.thriftera.constants.USER_COLLECTION
import com.groupproject.thriftera.data.CartProductNew
import com.groupproject.thriftera.firebase.FirebaseCommon
import com.groupproject.thriftera.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val firebaseCommon: FirebaseCommon
) : ViewModel() {

    private val _addToCart = MutableStateFlow<Resource<CartProductNew>>(Resource.Unspecified())
    val addToCart = _addToCart.asStateFlow()

    fun addUpdateProductInCart(cartProduct: CartProductNew) {
        viewModelScope.launch {
            try {
                _addToCart.emit(Resource.Loading())
                val userCartRef = firestore.collection(USER_COLLECTION)
                    .document(auth.uid!!)
                    .collection(CART_COLLECTION)
                    .whereEqualTo("product.id", cartProduct.productId)
                val querySnapshot = userCartRef.get().await()
                val documents = querySnapshot.documents
                if (documents.isEmpty()) {
                    addNewProduct(cartProduct)
                } else {
                        //updateProductQuantity()
//                    val existingProduct = documents.first().toObject(CartProduct::class.java)
//                    if (existingProduct?.product == cartProduct.product &&
//                        existingProduct.selectedColor == cartProduct.selectedColor &&
//                        existingProduct.selectedSize == cartProduct.selectedSize
//                    ) {
//                        val documentId = documents.first().id
//                        increaseQuantity(documentId, cartProduct)
//                    } else {
//                        addNewProduct(cartProduct)
//                    }
                }
            } catch (e: Exception) {
                _addToCart.emit(Resource.Error(e.message ?: "Error occurred"))
            }
        }
    }

    private fun addNewProduct(cartProduct: CartProductNew) {
        firebaseCommon.addProductToCart(cartProduct) { addedProduct, e ->
            viewModelScope.launch {
                if (e == null)
                    _addToCart.emit(Resource.Success(addedProduct!!))
                else
                    _addToCart.emit(Resource.Error(e.message.toString()))
            }
        }
    }

//    private fun increaseQuantity(documentId: String, cartProduct: CartProduct) {
//        firebaseCommon.increaseQuantity(documentId) { _, e ->
//            viewModelScope.launch {
//                if (e == null)
//                    _addToCart.emit(Resource.Success(cartProduct))
//                else
//                    _addToCart.emit(Resource.Error(e.message.toString()))
//            }
//        }
//    }
}










