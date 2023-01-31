package com.cocosystems.activityfragmentinstrumented.models

import java.io.Serializable

data class Product(
    val name: String,
    val price: Double,
    val description: String
): Serializable {
    companion object {
        fun mockData() = Product(
            "Pizza",
            149.90,
            "Pizza de pepperoni con extra queso."
        )

        fun getFakeData() = Product(
            "Pizza grande",
            99.90,
            "Pizza grande con extra queso, orilla rellenda de queso y salsa italiana."
        )
    }
}
