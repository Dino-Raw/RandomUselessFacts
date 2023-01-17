package com.example.domian.usecase

import com.example.domian.repository.Repository

class RemoveFactsFromStorageUseCase(private val repository: Repository) {
    fun execute(key: String) {
        repository.removeFacts(key = key)
    }
}