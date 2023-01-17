package com.example.randomuselessfacts.di

import com.example.randomuselessfacts.presentation.fragment.FactFragment
import com.example.randomuselessfacts.presentation.fragment.FavouriteFactsFragment
import com.example.randomuselessfacts.presentation.fragment.HistoryFactsFragment
import com.example.randomuselessfacts.presentation.viewmodel.FavouriteFactsViewModel
import com.example.randomuselessfacts.presentation.viewmodel.HistoryFactsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(factFragment: FactFragment)
    fun inject(historyFactsFragment: HistoryFactsFragment)
    fun inject(favouriteFactsFragment: FavouriteFactsFragment)
}