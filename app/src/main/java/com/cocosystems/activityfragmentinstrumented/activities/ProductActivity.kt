package com.cocosystems.activityfragmentinstrumented.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import com.cocosystems.activityfragmentinstrumented.R
import com.cocosystems.activityfragmentinstrumented.extensions.asMoney
import com.cocosystems.activityfragmentinstrumented.models.CartItem
import com.cocosystems.activityfragmentinstrumented.models.Product

class ProductActivity : BaseActivity() {
    // Views
    private lateinit var name: TextView
    private lateinit var price: TextView
    private lateinit var description: TextView
    private lateinit var quantity: TextView

    private lateinit var decrease: Button
    private lateinit var increase: Button
    private lateinit var total: Button

    // Model
    private lateinit var cartItem: CartItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        cartItem = CartItem(
            intent.extras?.let {
                it.getSerializable("product") as Product
            } ?: run {
                Product.mockData()
            }
        )

        setupViews()
        setupActions()
        renderData()
    }

    private fun setupViews() {
        // Text
        name = findView(R.id.name)
        price = findView(R.id.price)
        description = findView(R.id.description)

        // Buttons
        decrease = findView(R.id.decrease)
        increase = findView(R.id.increase)

        // Total
        total = findView(R.id.total)
        quantity = findView(R.id.quantity)
    }

    private fun renderData() {
        name.text = cartItem.product.name
        price.text = cartItem.product.price.asMoney()
        description.text = cartItem.product.description

        quantity.text = cartItem.quantity.toString()
        decrease.isEnabled = cartItem.quantity > 0

        val cartTotal = cartItem.calculateTotal()
        total.text = cartTotal.asMoney()
        total.isVisible = cartTotal > 0.0
    }

    private fun setupActions() {
        increase.setOnClickListener {
            cartItem.quantity++
            renderData()
        }

        decrease.setOnClickListener {
            cartItem.quantity--
            renderData()
        }
    }
}