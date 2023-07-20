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
import com.example.gamestores.data.models.Game
import com.example.gamestores.databinding.ItemQuoteBinding

class GameAdapter(var games: List<Game>) :
    RecyclerView.Adapter<GameAdapter.QuoteViewHolder>() {
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
        return games.size
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = games[position]
        holder.bind(item)

    }

    companion object {
        @JvmStatic
        @BindingAdapter("app:quoteList")
        fun setQuoteList(recyclerView: RecyclerView, gameList: List<Game>?) {
            if (gameList != null) {
                val adapter = recyclerView.adapter as GameAdapter
                adapter.games = gameList
            }
        }

        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun loadImageUrl(imageView: ImageView, imageUrl: String?) {
            imageUrl.let { url ->
                val imageLoader = ImageLoader.Builder(imageView.context)
                    .build()
                imageView.load(url, imageLoader)
            }

        }
    }

    private var clickListener: ClickListener? = null

    interface ClickListener {
        fun onClick(quote: Game)

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
                    val quote = games[position]
                    clickListener?.onClick(quote)
                }
            }
        }

        fun bind(game: Game) {
            binding.quote = game
            binding.executePendingBindings()

        }
    }
}

