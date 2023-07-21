package com.example.gamestores.data.repository

import android.util.Log
import com.example.gamestores.data.models.Game
import com.example.gamestores.data.models.GameDetail
import com.example.gamestores.data.network.StoresApi
import com.example.gamestores.data.network.RetrofitHelper

class GamesRepository {
    private val storesApi: StoresApi = RetrofitHelper.getInstance().create(StoresApi::class.java)
    suspend fun getGameList(): List<Game> {
        val result = storesApi.getListGame()
        val games = result.body()
        return games!!
    }
    suspend fun getGameDetail(gameId:Int): GameDetail {
        val result = storesApi.getGameDetail(gameId)
        val game = result.body()
        return game!!
    }
}