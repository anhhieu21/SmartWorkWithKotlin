package com.example.gamestores.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.example.gamestores.R
import com.example.gamestores.databinding.CarouselItemBinding

class CarouselAdapter(private val items: List<String>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarouselAdapter.CarouselViewHolder {
        val binding: CarouselItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.carousel_item,
            parent,
            false
        )
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselAdapter.CarouselViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    companion object {


        @JvmStatic
        @BindingAdapter("app:imageUrlCarousel")
        fun loadImageUrl(imageView: ImageView, imageUrl: String?) {
            imageUrl.let { url ->
                val imageLoader = ImageLoader.Builder(imageView.context)
                    .build()
                imageView.load(url, imageLoader)
            }

        }
    }

    inner class CarouselViewHolder(private val binding: CarouselItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: String) {
            binding.value = value
            binding.executePendingBindings()
        }
    }
}