package com.example.first_project

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MyProfileActivity : AppCompatActivity() {

    private lateinit var sp : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        val tvUserEmail = findViewById<TextView>(R.id.tv_userEmail)
        val tvUsername = findViewById<TextView>(R.id.tv_name)
        val btnLogout = findViewById<Button>(R.id.btn_logout)
        val backBtn = findViewById<ImageView>(R.id.iv_backBtn)

        sp = getSharedPreferences("my_sp", MODE_PRIVATE)
        editor = sp.edit()

        val name = sp.getString("sp_name", "")
        val email = sp.getString("sp_email", "")

        tvUsername.text = name
        tvUserEmail.text = email

        backBtn.setOnClickListener {
            finish()
        }

        btnLogout.setOnClickListener {

            editor.apply {
                putBoolean("isRememberedMe", false)
                commit()
            }

            val intent =  Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}