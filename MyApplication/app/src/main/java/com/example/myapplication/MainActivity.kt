package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MYTAG", "MainActivity : OnCreate")


         val fragmentA  = MyFragment1()
         val fragmentB  = MyFragment2()

         val btnA = findViewById<Button>(R.id.btn_A)
         val btnB = findViewById<Button>(R.id.btn_B)

         btnA.setOnClickListener {
             if (!fragmentA.isAdded)
                 supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frameLayout, fragmentA).commit()
         }

         btnB.setOnClickListener {
             if (!fragmentB.isAdded)
                 supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frameLayout, fragmentB).commit()
         }

//
//         val greetingTextView = findViewById<TextView>(R.id.tvHello)
//         val inputField = findViewById<EditText>(R.id.etName)
//         val submitBtn = findViewById<Button>(R.id.tvSubmitBtn)
//         val offersbtn = findViewById<Button>(R.id.btnOffers)
//         var enteredName = ""
//
//        submitBtn.setOnClickListener{
//            enteredName = inputField.text.toString()
//            if (enteredName == ""){
//                offersbtn.visibility = INVISIBLE
//                greetingTextView.text = ""
//                Toast.makeText(this@MainActivity, "Please enter your name.", Toast.LENGTH_LONG).show()
//            }else {
//                val message = "Welcome $enteredName"
//                greetingTextView.text = message
//                inputField.text.clear()
//                offersbtn.visibility = VISIBLE
//            }
//        }
//
//        offersbtn.setOnClickListener{
//            val intent = Intent(this@MainActivity, SecondActivity::class.java)
//            intent.putExtra("USER", enteredName)
//            startActivity(intent)
//        }

    }

    override fun onStart() {
        super.onStart()
        Log.i("MYTAG", "MainActivity : OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MYTAG", "MainActivity : OnResume")
    }


    override fun onPause() {
        super.onPause()
        Log.i("MYTAG", "MainActivity : OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MYTAG", "MainActivity : OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MYTAG", "MainActivity : OnDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MYTAG", "MainActivity : OnRestart")
    }
}