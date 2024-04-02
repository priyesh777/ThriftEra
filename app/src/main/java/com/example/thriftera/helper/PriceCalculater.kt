package com.example.thriftera.helper

fun Float?.getProductPrice(price: Float): Float{
    //this --> Percentage
    if (this == null)
        return price
    val remainingPricePercentage = 1f - this
    val priceAfterOffer = remainingPricePercentage * price

    return priceAfterOffer
}


//a function that discounts the percentage from price
fun getProductPriceAfterDiscount(originalPrice: Float, percent: Float): Float {
    return originalPrice - (originalPrice * percent/100)
}