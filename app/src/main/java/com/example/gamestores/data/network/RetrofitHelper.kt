package com.example.gamestores.data.network

import okhttp3.Headers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Callback

object RetrofitHelper {
    private const val baseUrl = "https://www.freetogame.com/api/"
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}

// Tạo OkHttpClient với Interceptor
fun createOkHttpClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(HeaderInterceptor())
    return httpClient.build()
}

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val headers = Headers.Builder()
            .add("X-RapidAPI-Host", "free-to-play-games-database.p.rapidapi.com")
            .add("X-RapidAPI-Key", "c7908692c2msh1b4783144b1f48dp148df4jsn741b66368d2d")
            .build()

        val newRequest = request.newBuilder().headers(headers).build()
        return chain.proceed(newRequest)
    }
}