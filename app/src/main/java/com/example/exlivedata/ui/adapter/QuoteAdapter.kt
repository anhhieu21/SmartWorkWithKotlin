package com.example.exlivedata.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.example.exlivedata.R
import com.example.exlivedata.data.models.Result
import com.example.exlivedata.databinding.ItemQuoteBinding

class QuoteAdapter(var quoteList: List<Result>) :
    RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding: ItemQuoteBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_quote,
            parent,
            false
        )
        return QuoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return quoteList.size
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = quoteList[position]
        holder.bind(item)

    }

    companion object {
        @JvmStatic
        @BindingAdapter("app:quoteList")
        fun setQuoteList(recyclerView: RecyclerView, quoteList: List<Result>?) {
            if (quoteList != null) {
                val adapter = recyclerView.adapter as QuoteAdapter
                adapter.quoteList = quoteList
            }
        }

        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun loadImageUrl(imageView: ImageView, imageUrl: String?) {
            val x =
                "https://plus.unsplash.com/premium_photo-1669631945645-98bf6931f5d0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80"
            val imageLoader = ImageLoader.Builder(imageView.context)
                .build()
            imageView.load(x, imageLoader)
        }
    }

    private var clickListener: ClickListener? = null

    interface ClickListener {
        fun onClick(quote: Result)

    }

    fun setClickListener(listener: ClickListener) {
        this.clickListener = listener
    }

    inner class QuoteViewHolder(private val binding: ItemQuoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.item.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val quote = quoteList[position]
                    clickListener?.onClick(quote)
                }
            }
        }

        fun bind(result: Result) {
            binding.quote = result
            binding.executePendingBindings()

        }
    }
}

