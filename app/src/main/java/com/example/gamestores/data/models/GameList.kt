package com.example.gamestores.data.models

data class GameList(
    val count : Int,
    val lastItemIndex: Int,
    val page: Int,
    val games: List<Game>,
    val totalCount: Int,
    val totalPages: Int
)
