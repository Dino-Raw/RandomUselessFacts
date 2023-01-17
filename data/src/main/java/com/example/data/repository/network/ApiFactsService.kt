package com.example.data.repository.network

import com.example.data.model.FactNetworkModel
import retrofit2.http.GET

interface ApiFactsService {
    @GET("/random.json?language=en")
    suspend fun getRandomFact(): FactNetworkModel

    @GET("/today.json?language=en")
    suspend fun getDailyFact(): FactNetworkModel
}