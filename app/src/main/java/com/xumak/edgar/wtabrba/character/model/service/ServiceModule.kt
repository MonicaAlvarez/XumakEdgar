package com.xumak.edgar.wtabrba.character.model.service

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ServiceModule {
    private val BASE_URL = "https://www.breakingbadapi.com/api/"

    @Provides
    fun providesRetorfit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesLyricsService() : ApiService {
        return  providesRetorfit().create(ApiService::class.java)
    }
}