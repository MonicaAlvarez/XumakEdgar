package com.xumak.edgar.wtabrba.character.model.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("characters")
    fun requestChacter(@QueryMap options: Map<String, String>) : Call<List<ObjectResponse.Character>>
}