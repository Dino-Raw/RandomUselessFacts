package com.example.domian.usecase

import com.example.domian.model.FactModel
import com.example.domian.repository.Repository

class LoadFactsFromStorageUseCase(private val repository: Repository) {
    fun execute(key: String): List<FactModel> {
        return repository.loadFacts(key = key)
    }
}