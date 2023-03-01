package com.example.recyclerviewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdemo.adapters.MyRecyclerViewAdapter
import com.example.recyclerviewdemo.data.Fruit

class MainActivity : AppCompatActivity() {

    val fruitsList = listOf<Fruit>(
        Fruit("Mango", "ABC"),
        Fruit("Banana", "CDE"),
        Fruit("Pear", "MNO"),
        Fruit("Apple", "XYZ"),
        Fruit("Melon", "PQR"),
        Fruit("Berries", "UVW"),
        Fruit("Orange", "EFG"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.myRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = MyRecyclerViewAdapter(fruitsList, {
            selectedItem : Fruit -> listItemClicked(selectedItem)
        })
    }

    private fun listItemClicked(fruit : Fruit){
        Toast.makeText(this@MainActivity, "Selected fruit is ${fruit.name}", Toast.LENGTH_SHORT).show()
    }
}