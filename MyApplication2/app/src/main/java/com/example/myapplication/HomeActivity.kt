package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onResume() {
        super.onResume()
        var sp = getSharedPreferences("my_sp", MODE_PRIVATE)
        val name = sp.getString("sp_name", "")
        val age = sp.getInt("sp_age", 0) // Second argument is the default value


        if (name?.length === 0 || age?.toString()?.length == 0) {
          finish()
        }
    }
}