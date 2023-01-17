package com.example.randomuselessfacts.di

import com.example.domian.repository.Repository
import com.example.domian.usecase.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetRandomFact(repository: Repository) =
        GetRandomFactUseCase(repository = repository)

    @Provides
    fun provideGetDailyFact(repository: Repository) =
        GetDailyFactUseCase(repository = repository)

    @Provides
    fun provideSaveFactsToStorage(repository: Repository) =
        SaveFactsToStorageUseCase(repository = repository)

    @Provides
    fun provideLoadFactsFromStorage(repository: Repository) =
        LoadFactsFromStorageUseCase(repository = repository)

    @Provides
    fun provideRemoveFactsFromStorage(repository: Repository) =
        RemoveFactsFromStorageUseCase(repository = repository)

    // fun provideSaveFactsToFavourite(repository: Repository) = SaveFactsToFavourite(repository = repository)
    // fun provideLoadFactsToFavourite(repository: Repository) = LoadFactsToFavourite(repository = repository)
    // fun provideRemoveFactsToFavourite(repository: Repository) = RemoveFactsToFavourite(repository = repository)
}