package com.example.first_project

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.first_project.adapters.HomeProductAdapter
import com.example.first_project.models.ProductItem

class ProductViewActivity : AppCompatActivity(), HomeProductAdapter.HomeProductListener {
    lateinit var cartQty : TextView
    lateinit var product : ProductItem
    private lateinit var  addToCartBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_view)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvPrice = findViewById<TextView>(R.id.tvPrice)
        val tvDesc = findViewById<TextView>(R.id.tvDesc)
        val iv1Item = findViewById<ImageView>(R.id.iv1)
        val iv2Item = findViewById<ImageView>(R.id.iv2)
        val backBtn = findViewById<ImageView>(R.id.iv_backBtn)
        val cart = findViewById<ImageView>(R.id.iv_cart)
        addToCartBtn = findViewById<Button>(R.id.btnAddToCart)
        cartQty = findViewById(R.id.cart_item_count)

        product = intent.extras?.getParcelable<ProductItem>("product" ) as ProductItem

        iv1Item.setImageResource(product.img)
        iv2Item.setImageResource(product.img)
        tvTitle.text = product.name
        tvPrice.text = "$"+product.price.toString()
        tvDesc.text = product.category

        cart.setOnClickListener{
            val intent = Intent(this@ProductViewActivity, CartActivity::class.java)
            startActivityForResult(intent, 0)
        }

        backBtn.setOnClickListener{
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        updateCartQty()
        checkItemInCart()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val deletedItems = data?.getIntegerArrayListExtra("deletedItems")

        val ind = deletedItems?.indexOfFirst {
            product.id === it
        }

        if (ind != -1)
            addToCartBtn.text = "Add to Cart"

        val intent  = Intent()
        intent.putIntegerArrayListExtra("deletedItems", deletedItems)
        this.setResult(0, intent)
    }

    private fun goToCart (context : Context){
        val intent = Intent(context, CartActivity::class.java )
        startActivityForResult(intent, 2)
    }

    private fun checkItemInCart(){
        val cartList = UserCart.getCart()

        val ind  = cartList.indexOfFirst {
            it.product.id == product.id
        }

        if (ind != -1){
            addToCartBtn.text = "Go to Cart"
            addToCartBtn.setOnClickListener {
                goToCart(this)
            }
        }

        else {
            addToCartBtn.text = "Add to Cart"
            addToCartBtn.setOnClickListener{
                UserCart.addToCart(product)
                addToCartBtn.text = "Go to Cart"
                updateCartQty()
                addToCartBtn.setOnClickListener {
                    goToCart(this)
                }
            }
        }
    }

    override fun updateCartQty() {
        var netQty = 0;
        val cartItems = UserCart.getCart()
        for (ele in cartItems){
            Log.d("cart item", ele.toString())
            netQty += ele.quantity
        }

        cartQty.text = netQty.toString()
    }
}
