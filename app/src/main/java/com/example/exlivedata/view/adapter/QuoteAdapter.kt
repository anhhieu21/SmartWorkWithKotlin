package com.example.exlivedata.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exlivedata.R
import com.example.exlivedata.data.models.Result

class QuoteAdapter(var dataList: List<Result>): RecyclerView.Adapter<QuoteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_quote,parent,false)
        return  QuoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  dataList.size
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item =dataList[position]
       holder.title.text= item.author
       holder.content.text= item.content
       holder.date.text= item.dateAdded
    }
}
class QuoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.txt_title)
    val content: TextView = view.findViewById(R.id.txt_content)
    val date: TextView = view.findViewById(R.id.txt_date)
}
