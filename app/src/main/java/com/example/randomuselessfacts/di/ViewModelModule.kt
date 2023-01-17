package com.example.randomuselessfacts.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomuselessfacts.presentation.viewmodel.FactViewModel
import com.example.randomuselessfacts.presentation.viewmodel.FavouriteFactsViewModel
import com.example.randomuselessfacts.presentation.viewmodel.HistoryFactsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FactViewModel::class)
    fun bindFactViewModel(factViewModel: FactViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryFactsViewModel::class)
    fun bindHistoryFactsViewModel(historyFactsViewModel: HistoryFactsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteFactsViewModel::class)
    fun bindFavouriteFactsViewModel(favouriteFactsViewModel: FavouriteFactsViewModel): ViewModel
}