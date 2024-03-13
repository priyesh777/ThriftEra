package com.example.thriftera.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartProduct(
    // Data types for cart products
    val product: Product,
    val quantity: Int,
    val selectedColor: Int? = null,
    val selectedSize: String? = null
) : Parcelable {
    constructor() : this(Product(), 1, null, null)
}

@Parcelize
data class CartProductNew(
    val productId: String,
    val quantity: Int,
    val selectedColor: Int? = null,
    val selectedSize: String? = null
): Parcelable {
    constructor(): this("", 1, null, null)
}