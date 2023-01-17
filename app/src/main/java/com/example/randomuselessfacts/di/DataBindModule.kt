package com.example.randomuselessfacts.di

import com.example.data.repository.RepositoryImpl
import com.example.data.repository.storage.FactsStorage
import com.example.data.repository.storage.FactsStorageImpl
import com.example.domian.repository.Repository
import dagger.Binds
import dagger.Module

@Module
interface DataBindModule {
    @Binds
    fun bindsRepository(impl: RepositoryImpl): Repository
    @Binds
    fun bindsFactsStorage(impl: FactsStorageImpl): FactsStorage

}