package com.example.gamestores.data.network

import com.example.gamestores.data.models.Game
import com.example.gamestores.data.models.GameDetail
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StoresApi {
    @GET("games")
    suspend fun getListGame(): Response<List<Game>>

    @GET("game")
    suspend fun getGameDetail(@Query("id") gameId:Int) : Response<GameDetail>
}

