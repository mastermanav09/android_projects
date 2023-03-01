package com.example.first_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.first_project.adapters.HomeProductAdapter
import com.example.first_project.models.ProductItem

class MainActivity : AppCompatActivity() , HomeProductAdapter.HomeProductListener {
    private lateinit var rvProducts : RecyclerView
    private lateinit var cartQty : TextView
    private lateinit var profile : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvProducts = findViewById<RecyclerView>(R.id.rvHomeProducts)
        cartQty = findViewById(R.id.cart_item_count)
        profile = findViewById(R.id.iv_profile)
//        var btnCart = findViewById<TextView>(R.id.item_addToCartBtn)
        val cart = findViewById<ImageView>(R.id.iv_cart)
        val obj = Products()

        rvProducts.adapter = HomeProductAdapter(obj.list, this)
        rvProducts.layoutManager = GridLayoutManager(this, 2)

        profile.setOnClickListener{
            val intent = Intent(this, MyProfileActivity::class.java)
            startActivity(intent)
        }

        cart.setOnClickListener{
            val intent = Intent(this@MainActivity, CartActivity::class.java)
            startActivityForResult(intent, 0)
        }



    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val deletedItems = data?.getIntegerArrayListExtra("deletedItems")

        deletedItems?.forEach{
            rvProducts.adapter?.notifyItemChanged(it- 1)
        }

        updateCartQty()
    }

    override fun updateCartQty() {
        var netQty = 0
        val cartItems = UserCart.getCart()
        for (ele in cartItems){
            netQty += ele.quantity
        }

        cartQty.text = netQty.toString()
    }

}

class Products {
    val list = arrayListOf<ProductItem>(
        ProductItem(R.drawable.img1, 1, "Jeans", "duqwhaduwhiohdoidhoihwodhwaoidhaiwdhodihaiwdhdwaoidhawiodwhadiawhdhwadhawidhwaiodhwaiodhwadhiwahiwdhhwaidhwodhwaiohdioawhdoaiwdhawiodhawoidhawihddhawiodhwaiodhawiodwahdioahdiodhawidhwiodahwdihdahdiohwaoidhdoiwahdoiawhdwadhwahqwhaduwhiohdoidhoihwodhwaoidhaiwdhodihaiwdhdwaoidhawiodwhadiawhdhwadhawidhwaiodhwaiodhwadhiwahiwdhhwaidhwodhwaiohdioawhdoaiwdhawiodhawoidhawihddhawiodhwaiodhawiodwahdioahdiodqwhaduwhiohdoidhoihwodhwaoidhaiwdhodihaiwdhdwaoidhawiodwhadiawhdhwadhawidhwaiodhwaiodhwadhiwahiwdhhwaidhwodhwaiohdioawhdoaiwdhawiodhawoidhawihddhawiodhwaiodhawiodwahdioahdiodqwhaduwhiohdoidhoihwodhwaoidhaiwdhodihaiwdhdwaoidhawiodwhadiawhdhwadhawidhwaiodhwaiodhwadhiwahiwdhhwaidhwodhwaiohdioawhdoaiwdhawiodhawoidhawihddhawiodhwaiodhawiodwahdioahdiodqwhaduwhiohdoidhoihwodhwaoidhaiwdhodihaiwdhdwaoidhawiodwhadiawhdhwadhawidhwaiodhwaiodhwadhiwahiwdhhwaidhwodhwaiohdioawhdoaiwdhawiodhawoidhawihddhawiodhwaiodhawiodwahdioahdiodqwhaduwhiohdoidhoihwodhwaoidhaiwdhodihaiwdhdwaoidhawiodwhadiawhdhwadhawidhwaiodhwaiodhwadhiwahiwdhhwaidhwodhwaiohdioawhdoaiwdhawiodhawoidhawihddhawiodhwaiodhawiodwahdioahdiodqwhaduwhiohdoidhoihwodhwaoidhaiwdhodihaiwdhdwaoidhawiodwhadiawhdhwadhawidhwaiodhwaiodhwadhiwahiwdhhwaidhwodhwaiohdioawhdoaiwdhawiodhawoidhawihddhawiodhwaiodhawiodwahdioahdiodqwhaduwhiohdoidhoihwodhwaoidhaiwdhodihaiwdhdwaoidhawiodwhadiawhdhwadhawidhwaiodhwaiodhwadhiwahiwdhhwaidhwodhwaiohdioawhdoaiwdhawiodhawoidhawihddhawiodhwaiodhawiodwahdioahdiodqwhaduwhiohdoidhoihwodhwaoidhaiwdhodihaiwdhdwaoidhawiodwhadiawhdhwadhawidhwaiodhwaiodhwadhiwahiwdhhwaidhwodhwaiohdioawhdoaiwdhawiodhawoidhawihddhawiodhwaiodhawiodwahdioahdiodqwhaduwhiohdoidhoihwodhwaoidhaiwdhodihaiwdhdwaoidhawiodwhadiawhdhwadhawidhwaiodhwaiodhwadhiwahiwdhhwaidhwodhwaiohdioawhdoaiwdhawiodhawoidhawihddhawiodhwaiodhawiodwahdioahdiodqwhaduwhiohdoidhoihwodhwaoidhaiwdhodihaiwdhdwaoidhawiodwhadiawhdhwadhawidhwaiodhwaiodhwadhiwahiwdhhwaidhwodhwaiohdioawhdoaiwdhawiodhawoidhawihddhawiodhwaiodhawiodwahdioahdiodqwhaduwhiohdoidhoihwodhwaoidhaiwdhodihaiwdhdwaoidhawiodwhadiawhdhwadhawidhwaiodhwaiodhwadhiwahiwdhhwaidhwodhwaiohdioawhdoaiwdhawiodhawoidhawihddhawiodhwaiodhawiodwahdioahdiod", 18),
        ProductItem(R.drawable.img2, 2, "Phone", "DEF", 300),
        ProductItem(R.drawable.img3, 3, "Phone", "FGH", 1800),
        ProductItem(R.drawable.img4, 4, "Sweatshirt", "IJK", 19),
        ProductItem(R.drawable.img5, 5, "Shoes", "LMN", 25),
        ProductItem(R.drawable.img6, 6, "Laptop", "XYZ", 1500),
    )
}
