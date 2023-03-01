package com.example.retrofit_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_demo.adapters.HomeAdapter
import com.google.gson.Gson
import retrofit2.*

class MainActivity : AppCompatActivity()  {
    private lateinit  var retrofit: Retrofit
    private lateinit var apiInterface: ApiInterface
    private lateinit var rvItems : RecyclerView
    private lateinit var tvData : TextView
    private lateinit var evPageNumber : EditText
    private lateinit var btn_getPage : Button
    var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvItems = findViewById(R.id.rv_items)
        initData()
    }

    private fun initData(){
//        tvData = findViewById(R.id.tv_)
        evPageNumber= findViewById(R.id.ev_pageNumber)
        btn_getPage = findViewById(R.id.btn_getPage)
        progressBar = findViewById(R.id.progressBar)

        retrofit = RetrofitInstance.getInstance()
        apiInterface = retrofit.create(ApiInterface::class.java)

        btn_getPage.setOnClickListener {
            getData()
        }
    }

    private fun getData(){

        progressBar?.visibility = View.VISIBLE
        apiInterface.getData(evPageNumber.text.toString().toInt()).enqueue(object : Callback<ReqResModel?> {

            override fun onResponse(
                call: Call<ReqResModel?>,
                response: Response<ReqResModel?>
            ) {
                progressBar?.visibility = View.GONE
                rvItems.adapter = response.body()?.let { HomeAdapter(this@MainActivity, it.data) }
                rvItems.layoutManager = GridLayoutManager(this@MainActivity, 2)
            }

            override fun onFailure(call: Call<ReqResModel?>, t: Throwable) {
                progressBar?.visibility = View.GONE
                t.printStackTrace()

               Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
            }
        })
    }

}