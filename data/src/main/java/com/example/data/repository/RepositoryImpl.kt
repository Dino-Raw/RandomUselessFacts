package com.example.data.repository

import com.example.data.model.FactNetworkModel
import com.example.data.model.FactStorageModel
import com.example.data.repository.network.ApiFactsService
import com.example.data.repository.storage.FactsStorage
import com.example.domian.model.FactModel
import com.example.domian.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiFactsService: ApiFactsService,
    private val factsStorage: FactsStorage
    ): Repository {
    override suspend fun getDailyFact(): FactModel {
        return factNetworkModelToFactModel(fact = apiFactsService.getDailyFact())
    }

    override suspend fun getRandomFact(): FactModel {
        return factNetworkModelToFactModel(fact = apiFactsService.getRandomFact())
    }

    override fun saveFacts(key: String, facts: List<FactModel>) {
        factsStorage.saveFacts(key = key, facts = listFactsModelToListFactsStorageModel(facts))
    }

    override fun loadFacts(key: String): List<FactModel> {
        return listFactsStorageModelToListFactsModel(factsStorage.loadFacts(key = key))
    }

    override fun removeFacts(key: String) {
        factsStorage.removeFacts(key = key)
    }

    private fun listFactsModelToListFactsStorageModel(facts: List<FactModel>): List<FactStorageModel> {
        val factsData = mutableListOf<FactStorageModel>()
        facts.forEach { factsData.add(FactStorageModel(text = it.text)) }
        return factsData.toList()
    }

    private fun listFactsStorageModelToListFactsModel(facts: List<FactStorageModel>): List<FactModel> {
        val factsData = mutableListOf<FactModel>()
        facts.forEach { factsData.add(FactModel(text = it.text)) }
        return factsData.toList()
    }

    private fun factNetworkModelToFactModel(fact: FactNetworkModel): FactModel {
        return FactModel(text = fact.text)
    }
}