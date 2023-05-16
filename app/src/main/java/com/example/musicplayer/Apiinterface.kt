package com.example.musicplayer

import retrofit2.Call
import retrofit2.http.GET

interface Apiinterface {
    @GET("spotify23")                        //last word of api url on website
fun getProductData() : Call<Mydata>
}