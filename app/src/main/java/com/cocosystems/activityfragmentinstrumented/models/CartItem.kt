package com.cocosystems.activityfragmentinstrumented.models

class CartItem(
    val product: Product,
    var quantity: Int = 0
) {
    fun calculateTotal(): Double = quantity * product.price
}