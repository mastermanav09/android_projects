package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var nameText : EditText
    private lateinit var ageText : EditText
    private lateinit var sp : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameText = findViewById(R.id.etName)
        ageText = findViewById((R.id.etAge))

        sp = getSharedPreferences("my_sp", MODE_PRIVATE)
        editor = sp.edit()

        var loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener {
            val name = nameText.text.toString()
            val age = ageText.text.toString()

            if (name.length !== 0 &&  age.toString().length !== 0){
                editor.apply {
                    putString("sp_name", name)
                    putInt("sp_age", age.toInt())
                    commit() // Important step otherwise values will not be saved
                }

                var intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)

                nameText.text.clear()
                ageText.text.clear()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val name = sp.getString("sp_name", "")
        val age = sp.getInt("sp_age", 0) // Second argument is the default value
        nameText.setText(name)

        if (age != 0)
            ageText.setText(age.toString())

        if (name?.length !== 0 && age?.toString()?.length !== 0) {
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }

}