package com.example.viewmodel_recyclerview_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this@MainActivity).get(MainActivityViewModel::class.java)
        val countBtn = findViewById<Button>(R.id.btnCount)
        val countText = findViewById<TextView>(R.id.tvCount)

//      countText.text = count.toString()
        countText.text = viewModel.count.toString()

        countBtn.setOnClickListener {
            viewModel.updateCount()
            countText.text = viewModel.count.toString()
        }

    }
}