package com.example.gamestores.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gamestores.R
import com.example.gamestores.data.models.Game
import com.example.gamestores.data.models.GameList
import com.example.gamestores.databinding.ItemTopChartBinding

class TopChartAdapter(private val games: List<Game>) :
    RecyclerView.Adapter<TopChartAdapter.TopChartViewHolder>() {
    inner class TopChartViewHolder(private val binding: ItemTopChartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: Game) {
            binding.quote = value
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopChartViewHolder {
        val binding: ItemTopChartBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_top_chart, parent, false
        )

        return TopChartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: TopChartViewHolder, position: Int) {
        val item = games[position]
        holder.bind(item)
    }
}