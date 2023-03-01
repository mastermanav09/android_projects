package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)

        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if (validateInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val revisedBMI = String.format("%.2f", bmi).toFloat()
                displayResult(revisedBMI)
            }
        }

    }


    private fun validateInput(weight:String?, height:String?) : Boolean{
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this@MainActivity, "Weight is incorrect", Toast.LENGTH_LONG).show()
                return false
            }

            height.isNullOrEmpty() -> {
                Toast.makeText(this@MainActivity, "Height is incorrect", Toast.LENGTH_LONG).show()
                return false
            }

            else ->{
                return true
            }

        }

    }

    private fun displayResult(bmi : Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDesc = findViewById<TextView>(R.id.tvResult)

        resultIndex.text = bmi.toString()

        var resultText :String
        var color : Int

        if (bmi < 18.50){
            resultText = "Underweight"
            color = R.color.underweight
        }
        else if (bmi in 18.50..24.99){
            resultText = "Healthy"
            color = R.color.normal
        }
        else if (bmi in 25.00..29.99){
            resultText = "Overweight"
            color = R.color.overweight
        }
        else{
            resultText = "Obese"
            color = R.color.obese
        }


        resultDesc.text = resultText
        resultDesc.setTextColor(ContextCompat.getColor(this@MainActivity, color))

    }

}