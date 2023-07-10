package com.example.exlivedata.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exlivedata.data.QuoteRepository
import com.example.exlivedata.data.models.Result
import kotlinx.coroutines.launch

class QuoteViewModel:ViewModel() {
    private val quoteRepository = QuoteRepository()
    private val _quoteList = MutableLiveData<List<Result>>()
    val quoteList: LiveData<List<Result>>
        get() = _quoteList

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
}