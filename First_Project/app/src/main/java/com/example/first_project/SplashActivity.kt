package com.example.first_project

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.core.os.postDelayed

class SplashActivity : AppCompatActivity() {

    private lateinit var sp : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sp = getSharedPreferences("my_sp", MODE_PRIVATE)

        val isRememberedMe = sp.getBoolean("isRememberedMe", false)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler().postDelayed({

            var intent : Intent = if (isRememberedMe){
                Intent(
                    this@SplashActivity, MainActivity::class.java
                )

            } else{
                Intent(
                    this@SplashActivity, LoginActivity::class.java
                )
            }

            startActivity(intent)
            finish()
        }, 2000)

    }
}