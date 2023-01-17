package com.example.data.repository.storage

import com.example.data.model.FactStorageModel

interface FactsStorage {
    fun saveFacts(key: String, facts: List<FactStorageModel>)
    fun loadFacts(key: String): List<FactStorageModel>
    fun removeFacts(key: String)
}