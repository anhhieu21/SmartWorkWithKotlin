package com.example.exlivedata.data.network

import com.example.exlivedata.data.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {
    @GET("/quotes")
    suspend fun getQuotes(): Response<QuoteList>
}