package com.example.domian.usecase

import com.example.domian.repository.Repository

class GetRandomFactUseCase(private val repository: Repository) {
    suspend fun execute() = repository.getRandomFact()
}