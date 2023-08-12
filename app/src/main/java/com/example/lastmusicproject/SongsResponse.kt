package com.example.lastmusicproject

data class SongsResponse(
    var songs:List<Song>,
    var total:Int,
    var skip:Int,
    var limit:Int

)

