package com.example.exlivedata.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.exlivedata.R
import com.example.exlivedata.data.models.Result
import com.example.exlivedata.databinding.ActivityMainBinding
import com.example.exlivedata.view.adapter.QuoteAdapter
import com.example.exlivedata.view.viewmodel.QuoteViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var quoteAdapter: QuoteAdapter
    private lateinit var quoteViewModel: QuoteViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        quoteViewModel = ViewModelProvider(this)[QuoteViewModel::class.java]
        quoteAdapter = QuoteAdapter(emptyList())
        quoteAdapter.setClickListener(object : QuoteAdapter.ClickListener {
            override fun onClick(quote: Result) {
                removeItem(quote)
            }
        })
        binding.recyclerView.adapter = quoteAdapter
        binding.model = quoteViewModel
        binding.lifecycleOwner = this

        loadData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadData() {
        quoteViewModel.quoteList.observe(this) { quoteList ->
            quoteAdapter.quoteList = quoteList
            quoteAdapter.notifyDataSetChanged()
        }
        quoteViewModel.fetchQuotes()

//        quoteAdapter.setClickListener(object :QuoteAdapter.ClickListener{
//            override fun onClick(quote: Result) {
//
//            }
//
//        })
    }



    private fun removeItem(quote: Result) {
        quoteViewModel.removeQuote(quote)
    }
}