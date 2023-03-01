package com.example.first_project

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private lateinit var sp : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tvNavigateRegister = findViewById<TextView>(R.id.tv_navigateRegister)
        val cbRememberView = findViewById<CheckBox>(R.id.cb_rememberMe)
        val btnLogin = findViewById<Button>(R.id.btn_loginBtn)

        val ev_email = findViewById<EditText>(R.id.ev_email)
        val ev_password = findViewById<EditText>(R.id.ev_password)

        sp = getSharedPreferences("my_sp", MODE_PRIVATE)
        editor = sp.edit()

        tvNavigateRegister.setOnClickListener{
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {

            val userEmail = sp.getString("sp_email", "")
            val password = sp.getString("sp_password", "")

            if (ev_email.text.isEmpty() || ev_password.text.isEmpty()){
                Toast.makeText(this, "Incorrect details", Toast.LENGTH_LONG).show()
            }

            else if (userEmail?.isNotEmpty() == false && password?.isNotEmpty() == false) {
                Toast.makeText(this, "You are not registered, please register yourself first.", Toast.LENGTH_LONG).show()
            }

            else {
                if (ev_email.text.toString() == userEmail && ev_password.text.toString() == password){
                    editor.apply {
                        putBoolean("isRememberedMe", cbRememberView.isChecked)
                        commit()
                    }

                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                else {
                    Toast.makeText(this, "Incorrect details", Toast.LENGTH_LONG).show()
                }

            }

        }
    }
}