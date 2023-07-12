package com.example.exlivedata.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exlivedata.data.QuoteRepository
import com.example.exlivedata.data.models.Result
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {
    private val quoteRepository = QuoteRepository()
    private val _quoteList = MutableLiveData<List<Result>>()
    val quoteList: LiveData<List<Result>> get() = _quoteList

    private val _detail = MutableLiveData<Result>()
    val detail: LiveData<Result> get() = _detail
    fun fetchQuotes() {
        viewModelScope.launch {
            try {
                val quotes = quoteRepository.getQuotes()
                _quoteList.value = quotes
            } catch (e: Exception) {
                // Xử lý lỗi
            }
        }
    }

    fun removeQuote(quote: Result) {
        val list = _quoteList.value!!.toMutableList()
        list.remove(quote)
        _quoteList.value = list
    }

    fun detailQuote(quote: Result) {
        _detail.value = quote
    }
}