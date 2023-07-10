package com.example.exlivedata.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exlivedata.R
import com.example.exlivedata.data.network.QuotesApi
import com.example.exlivedata.data.network.RetrofitHelper
import com.example.exlivedata.view.adapter.QuoteAdapter
import com.example.exlivedata.view.viewmodel.QuoteViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var quoteListView: RecyclerView
    private lateinit var quoteAdapter: QuoteAdapter
    private lateinit var quoteViewModel : QuoteViewModel
    @SuppressLint("NotifyDataSetChanged")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quoteListView  = findViewById(R.id.list_quote)
        quoteListView.layoutManager = LinearLayoutManager(this)
        quoteAdapter = QuoteAdapter(emptyList())
        quoteListView.adapter = quoteAdapter

        quoteViewModel = ViewModelProvider(this).get(QuoteViewModel::class.java)
        quoteViewModel.quoteList.observe(this) { quotes ->
            quoteAdapter.dataList = quotes
            quoteAdapter.notifyDataSetChanged()
        }
        quoteViewModel.fetchQuotes()
    }

}