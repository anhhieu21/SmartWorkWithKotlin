package com.example.gamestores.data.models

data class Game(
    var id: Int,
    var title: String,
    var thumbnail: String,
    var shortDescription: String,
    var gameUrl: String,
    var genre: String,
    var platform: String,
    var publisher: String,
    var developer: String,
    var releaseDate: String,
    var freetogameProfileUrl: String
)

data class GameDetail(

    val id: Int,
    val title: String,
    val thumbnail: String,
    val status: String,
    val short_description: String,
    val description: String,
    val game_url: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    val release_date: String,
    val freetogame_profile_url: String,
    val minimum_system_requirements: MinimumSystemRequirements,
    val screenshots: List<Screenshot>
)

data class MinimumSystemRequirements(
    val os: String,
    val processor: String,
    val memory: String,
    val graphics: String,
    val storage: String
)

data class Screenshot(
    val id: Int,
    val image: String
)


