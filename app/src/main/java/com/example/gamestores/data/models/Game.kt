package com.example.gamestores.data.models

data class Game(
    var id                   : Int,
    var title                : String,
    var thumbnail            : String,
    var shortDescription     : String,
    var gameUrl              : String,
    var genre                : String,
    var platform             : String,
    var publisher            : String,
    var developer            : String,
    var releaseDate          : String,
    var freetogameProfileUrl : String
)
