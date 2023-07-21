package com.example.gamestores.ui.viewmodel

import android.graphics.drawable.Drawable
import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamestores.data.models.Game
import com.example.gamestores.data.models.GameDetail
import com.example.gamestores.data.repository.GamesRepository
import kotlinx.coroutines.launch


class GameViewModel : ViewModel() {
    private val gamesRepository = GamesRepository()

    private val _gameList = MutableLiveData<List<Game>>()
    val gameList: LiveData<List<Game>> get() = _gameList

    private val _detail = MutableLiveData<GameDetail>()
    val detail: LiveData<GameDetail> get() = _detail

    val btnWantViewModel = ButtonOptionViewModel()
    val btnPlayedViewModel = ButtonOptionViewModel()
    val btnAddGameListViewModel = ButtonOptionViewModel()
    val btnShareViewModel = ButtonOptionViewModel()


    init {
        fetchQuotes()
    }

    private fun fetchQuotes() {
        viewModelScope.launch {
            try {
                val quotes = gamesRepository.getGameList()

                _gameList.postValue(quotes)
                loadDataButtonBar()

            } catch (e: Exception) {
                Log.v("Error load game", e.toString())
            }
        }
    }

    fun removeQuote(quote: Game) {
        val list = _gameList.value!!.toMutableList()
        list.remove(quote)
        _gameList.value = list
    }

    fun detailGame(gameId: Int) {
        viewModelScope.launch {
            try {
                val res = gamesRepository.getGameDetail(gameId)
                _detail.postValue(res)
            } catch (e: Exception) {
                Log.v("Error load game", e.toString())
            }

        }
    }

    private fun loadDataButtonBar() {
        btnWantViewModel.label = "Want"
        btnPlayedViewModel.label = "Played"
        btnAddGameListViewModel.label = "Game List"
        btnShareViewModel.label = "Share"
    }


}

class ButtonOptionViewModel : ViewModel() {
    var iconSrc: Drawable? = null
    var label: String = ""
}