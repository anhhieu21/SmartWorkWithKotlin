package com.example.gamestores.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamestores.data.repository.GamesRepository
import com.example.gamestores.data.models.Game
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val gamesRepository = GamesRepository()
    private val _gameList = MutableLiveData<List<Game>>()
    val gameList: LiveData<List<Game>> get() = _gameList

    private val _detail = MutableLiveData<Game>()
    val detail: LiveData<Game> get() = _detail
    fun fetchQuotes() {
        viewModelScope.launch {
            try {
                val quotes = gamesRepository.getGameList()
                _gameList.value = quotes
                Log.v("fechgame",quotes.toString())
            } catch (e: Exception) {
                Log.v("Error load game",e.toString())
            }
        }
    }

    fun removeQuote(quote: Game) {
        val list = _gameList.value!!.toMutableList()
        list.remove(quote)
        _gameList.value = list
    }

    fun detailQuote(quote: Game) {
        _detail.value = quote
    }
}