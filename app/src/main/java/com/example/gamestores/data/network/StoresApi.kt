package com.example.gamestores.data.network

import com.example.gamestores.data.models.Game
import com.example.gamestores.data.models.GameList
import retrofit2.Response
import retrofit2.http.GET

interface StoresApi {
    @GET("games")
    suspend fun getListGame(): Response<List<Game>>
}