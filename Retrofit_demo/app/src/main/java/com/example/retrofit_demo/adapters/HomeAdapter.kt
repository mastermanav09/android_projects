package com.example.retrofit_demo.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_demo.Data
import com.example.retrofit_demo.R

class HomeAdapter (private val context : Context, private val items : List<Data>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = "${items[position].first_name} ${items[position].last_name}"
        holder.tv_email.text = items[position].email


        Glide.with(context)
            .load(items[position].avatar)
            .into(holder.iv_person)
    }
}


class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
    val tv_email = view.findViewById<TextView>(R.id.tvEmail)
    val tvName = view.findViewById<TextView>(R.id.tvName)
    val iv_person = view.findViewById<ImageView>(R.id.ivPerson)
}