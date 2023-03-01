package com.example.first_project.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.first_project.*
import com.example.first_project.models.CartItem
import com.example.first_project.models.ProductItem

class HomeProductAdapter(private val products : ArrayList<ProductItem>, private val context : Activity) : RecyclerView.Adapter<HomeProductAdapter.HomeProductViewHolder>() {

    inner class HomeProductViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val ivItem = view.findViewById<ImageView>(R.id.iv_item)
        val tvName  = view.findViewById<TextView>(R.id.tv_name)
        val tvPrice  = view.findViewById<TextView>(R.id.tv_price)
        val tvDesc = view.findViewById<TextView>(R.id.tv_category)
        val btnAddToCart  = view.findViewById<TextView>(R.id.item_addToCartBtn)
    }

    interface HomeProductListener {
        fun updateCartQty()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_view, parent, false)
        return HomeProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: HomeProductViewHolder, position: Int) {
        holder.tvName.text = products[position].name
        holder.tvPrice.text = "$${products[position].price}"

        var desc = products[position].category
        if (desc?.length!! > 12){
            desc = desc.substring(0, 12) + "...";
        }

        holder.tvDesc.text = desc
        holder.ivItem.setImageDrawable(ContextCompat.getDrawable(context, products[position].img))

        val cartList = UserCart.getCart()
        holder.itemView.setOnClickListener{
            val intent = Intent(context, ProductViewActivity::class.java)
            intent.putExtra("product", products[position])
            (context as MainActivity).startActivityForResult(intent, 0)
        }

        holder.btnAddToCart.text = "Add to Cart"
        holder.btnAddToCart.setOnClickListener{
           var product = products[position]

           val ind  = cartList.indexOfFirst {
               it.product.id == product.id
           }

           if (ind == -1) {
               holder.btnAddToCart.text = "Go to Cart"
               UserCart.addToCart(products[position])
               (context as MainActivity).updateCartQty()
           }

           else{
               val intent = Intent(context, CartActivity::class.java)
               context.startActivityForResult(intent,1)
           }
        }
    }
}
