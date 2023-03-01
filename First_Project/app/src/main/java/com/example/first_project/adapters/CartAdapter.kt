package com.example.first_project.adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.first_project.CartActivity
import com.example.first_project.MainActivity
import com.example.first_project.R
import com.example.first_project.UserCart
import com.example.first_project.models.CartItem


class CartAdapter(private val products : ArrayList<CartItem>, private val context : Context, private val clickListener : (spinner : Spinner) -> Unit): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    var tvOrderValue : TextView? = null
    var tvTotalValue : TextView? = null

    init{
        tvOrderValue = (context as CartActivity).findViewById(R.id.orderValue)
        tvOrderValue?.text = "0"

        if(tvOrderValue != null)
            tvOrderValue!!.text = "$${UserCart.getTotalPrice()}"

        tvTotalValue = (context as CartActivity).findViewById(R.id.totalPrice)
        if(tvTotalValue != null)
            tvTotalValue!!.text = "$${UserCart.getTotalPrice()}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cart_product_view, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = products[position].product
        holder.ivItem.setImageDrawable(ContextCompat.getDrawable(context, products[position].product.img))
        holder.tvName.text= product.name
        holder.tvPrice.text = "$${product.price}"

        var desc = product.category

        if (desc?.length!! > 20){
            desc = desc.substring(0, 20) + "..."
        }

        holder.tvDesc.text = desc

        holder.getSpinner(clickListener)
        holder.deleteBtn.setOnClickListener {
            CartActivity.deletedItems.add(products[position].product.id)
            UserCart.removeFromCart(product)
            notifyItemRemoved(position)
            updateCartTotal(tvOrderValue, tvTotalValue)
        }

        holder.spinner.setSelection(products[position].quantity - 1)
        holder.spinner.onItemSelectedListener = object : Activity(), AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
              UserCart.quantityChange(product.id, parent?.getItemAtPosition(pos).toString().toInt())
                updateCartTotal(tvOrderValue, tvTotalValue)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

     inner class CartViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val ivItem = view.findViewById<ImageView>(R.id.iv_item)
        val tvName  = view.findViewById<TextView>(R.id.tv_name)
        val tvPrice  = view.findViewById<TextView>(R.id.tv_price)
        val tvDesc = view.findViewById<TextView>(R.id.tv_category)
        val spinner = view.findViewById<Spinner>(R.id.items_spinner)
         val deleteBtn = view.findViewById<Button>(R.id.deleteFromCartBtn)

        fun getSpinner(clickListener: (spinner: Spinner) -> Unit){
            clickListener(spinner)
        }

    }
}

fun updateCartTotal(tvTotalValue : TextView? , tvOrderValue : TextView?){
    tvOrderValue!!.text = "$${UserCart.getTotalPrice()}"
    tvTotalValue!!.text = "$${UserCart.getTotalPrice()}"
}

