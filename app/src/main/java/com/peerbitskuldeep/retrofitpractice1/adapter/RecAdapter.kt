package com.peerbitskuldeep.retrofitpractice1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.peerbitskuldeep.retrofitpractice1.R
import com.peerbitskuldeep.retrofitpractice1.api.HomeData
import kotlinx.android.synthetic.main.custom_rec.view.*

class RecAdapter(var context: Context, var listData: ArrayList<HomeData>) : RecyclerView.Adapter<RecAdapter.RecViewHolder>(){

    var list = arrayListOf<HomeData>()

    init {

        list = listData

    }

    inner class RecViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.custom_rec, parent, false)
        return RecViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecViewHolder, position: Int) {

        var currentPosition = list[position]
        holder.itemView.apply {

            this.tvUserName.text = currentPosition.username
            this.tvCaption.text = currentPosition.caption

            Glide.with(context).load(currentPosition.image).into(imageView)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}