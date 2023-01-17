package com.example.domian.repository

import com.example.domian.model.FactModel

interface Repository {
    suspend fun getDailyFact(): FactModel
    suspend fun getRandomFact(): FactModel

    fun saveFacts(key: String, facts: List<FactModel>)
    fun loadFacts(key: String): List<FactModel>
    fun removeFacts(key: String)
}