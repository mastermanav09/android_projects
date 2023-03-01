package com.example.retrofit_demo

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/api/users") // endpoint
    fun getData(@Query("page") currentPage : Int) : retrofit2.Call<ReqResModel>
}