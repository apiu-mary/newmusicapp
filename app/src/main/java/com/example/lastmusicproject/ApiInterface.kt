package com.example.lastmusicproject

import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {
    @GET("/album")
    fun getAlbum(): Call<SongsResponse>
}
