package com.example.exlivedata.data

import com.example.exlivedata.data.models.Result
import com.example.exlivedata.data.network.QuotesApi
import com.example.exlivedata.data.network.RetrofitHelper

class QuoteRepository {
    private val quotesApi: QuotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
    suspend fun getQuotes(): List<Result> {
        val result = quotesApi.getQuotes()
        val quotes = result.body()?.results
        return quotes!!
    }
}