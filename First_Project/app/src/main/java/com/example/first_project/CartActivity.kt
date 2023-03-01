package com.example.first_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.first_project.adapters.CartAdapter
import com.example.first_project.models.CartItem
import com.example.first_project.models.ProductItem

class CartActivity : AppCompatActivity() {

    companion object{
        var deletedItems  = ArrayList<Int>()
    }

    lateinit var tvOrderValue : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        var rvCartProducts = findViewById<RecyclerView>(R.id.rvCartProducts)
        val backBtn = findViewById<ImageView>(R.id.iv_backBtn)

        tvOrderValue = findViewById(R.id.orderValue)
        val cartList = UserCart.getCart()

        rvCartProducts.adapter = CartAdapter(cartList, this, {
            spinner : Spinner -> initializeSpinner(spinner)
        })

        rvCartProducts.layoutManager = LinearLayoutManager(this)
        backBtn.setOnClickListener{
            finish()
        }

        val intent  = Intent()
        intent.putIntegerArrayListExtra("deletedItems", deletedItems)
        deletedItems.clear()
        this.setResult(2, intent)
    }

    private fun initializeSpinner(spinner : Spinner){
        ArrayAdapter.createFromResource(
            this,
            R.array.item_count,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("deletedItems", deletedItems)
        setResult(0, intent)

        super.onBackPressed()
    }
}


object UserCart {
    private var cartList = arrayListOf<CartItem>()
    private var totalPrice = 0

    fun getCart() : kotlin.collections.ArrayList<CartItem>
    {
        return cartList
    }

    fun getTotalPrice() : Int
    {
        return totalPrice
    }


    fun addToCart(product : ProductItem){
        val ind  = cartList.indexOfFirst {
            it.product.id == product.id
        }

        if (ind == -1)
            cartList.add(CartItem(product, 1))
        else
            cartList[ind].quantity++

        totalPrice += product.price
    }

    fun removeFromCart(product: ProductItem){
        val ind  = cartList.indexOfFirst {
            it.product.id == product.id
        }

        if (ind == -1)
            return

        val quantity = cartList[ind].quantity
        cartList.removeAt(ind);
        totalPrice -= quantity * product.price
    }


    fun quantityChange(id : Int, quantity : Int) {

        val ind = cartList.indexOfFirst {
            it.product.id == id
        }

        if (ind == -1)
            return

        totalPrice -= cartList[ind].quantity * cartList[ind].product.price
        totalPrice += quantity * cartList[ind].product.price

        cartList[ind].quantity = quantity
    }
}