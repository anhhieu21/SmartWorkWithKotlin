package com.example.gamestores.data.repository

import android.util.Log
import com.example.gamestores.data.models.Game
import com.example.gamestores.data.network.StoresApi
import com.example.gamestores.data.network.RetrofitHelper

class GamesRepository {
    private val storesApi: StoresApi = RetrofitHelper.getInstance().create(StoresApi::class.java)
    suspend fun getGameList(): List<Game> {
        val result = storesApi.getListGame()
        val games = result.body()
        return games!!
    }
}