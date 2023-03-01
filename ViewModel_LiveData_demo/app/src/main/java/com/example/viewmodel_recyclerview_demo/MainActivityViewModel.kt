package com.example.viewmodel_recyclerview_demo

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel(){
    var count = 0
    fun updateCount(){
        ++count
    }
}