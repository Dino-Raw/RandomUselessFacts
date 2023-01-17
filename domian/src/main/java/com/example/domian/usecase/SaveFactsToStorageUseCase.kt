package com.example.domian.usecase

import com.example.domian.model.FactModel
import com.example.domian.repository.Repository

class SaveFactsToStorageUseCase(private val repository: Repository) {
    fun execute(key: String, facts: List<FactModel>) {
        repository.saveFacts(key = key, facts = facts)
    }
}