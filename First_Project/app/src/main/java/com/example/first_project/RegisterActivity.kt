package com.example.first_project

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    private lateinit var sp : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sp = getSharedPreferences("my_sp", MODE_PRIVATE)
        editor = sp.edit()

        var evEmailPhone = findViewById<EditText>(R.id.ev_email)
        var evName = findViewById<EditText>(R.id.ev_name)
        var evPassword = findViewById<EditText>(R.id.ev_password)
        var evConfirmPassword = findViewById<EditText>(R.id.ev_confirmPassword)
        var btnRegister = findViewById<Button>(R.id.btn_registerBtn)
        var tvNavigateLogin = findViewById<TextView>(R.id.tv_navigateLogin)

        btnRegister.setOnClickListener {

            if (evEmailPhone.text.isNotEmpty() && evName.text.isNotEmpty() && evPassword.text.isNotEmpty() && evConfirmPassword.text.isNotEmpty()){
                if (setUpRegistration(evEmailPhone.text.toString(), evName.text.toString(), evPassword.text.toString(), evConfirmPassword.text.toString())){
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            else{
                Toast.makeText(this, "Please completely fill all details!", Toast.LENGTH_LONG)
                    .show()
            }
        }

        tvNavigateLogin.setOnClickListener{
            onBackPressed()
        }
    }

    private fun setUpRegistration(evEmailPhone : String, evName : String, evPassword : String, evConfirmPassword : String) : Boolean{

        if (evPassword != evConfirmPassword) {
            Toast.makeText(this, "Password and Confirm password are not same.", Toast.LENGTH_LONG)
                .show()

            return false;
        }
        else {
            editor.apply {
                putString("sp_email", evEmailPhone)
                putString("sp_name", evName)
                putString("sp_password", evPassword)
                putString("sp_confirmPassword", evConfirmPassword)
                commit()
            }

            return true
        }

    }
}




