package com.example.domian.usecase

import com.example.domian.model.FactModel
import com.example.domian.repository.Repository

class GetDailyFactUseCase(private val repository: Repository) {
    suspend fun execute(): FactModel {
        return repository.getDailyFact()
    }
}