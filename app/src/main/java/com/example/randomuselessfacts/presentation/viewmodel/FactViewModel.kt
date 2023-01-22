package com.example.randomuselessfacts.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domian.model.FactModel
import com.example.domian.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FactViewModel @Inject constructor(
    private val getDailyFactUseCase: GetDailyFactUseCase,
    private val getRandomFactUseCase: GetRandomFactUseCase,
    private val saveFactsToStorageUseCase: SaveFactsToStorageUseCase,
    private val loadFactsFromStorageUseCase: LoadFactsFromStorageUseCase,
    ): ViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)

    private val _fact: MutableLiveData<FactModel> = MutableLiveData()
    val fact: LiveData<FactModel> = _fact

    private val _message: MutableLiveData<String> = MutableLiveData("")
    val message: LiveData<String> = _message

    init {
        getDailyFact()
    }

    fun getDailyFact() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            _fact.postValue(getDailyFactUseCase.execute())
        }.invokeOnCompletion {
            saveHistoryFacts()
            isLoading.postValue(false)
        }
    }

    fun getRandomFact() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            _fact.postValue(getRandomFactUseCase.execute())
        }.invokeOnCompletion {
            saveHistoryFacts()
            isLoading.postValue(false)
        }
    }

    fun saveFavouriteFacts() {
        loadFavouriteFacts().also { list ->
            list.forEach { fact ->
                if (_fact.value?.text == fact.text) {
                    _message.value = "Fact already in favorites"
                    _message.value = ""
                    return
                }
            }
            _message.value = "Fact added to favorites"
            _message.value = ""
            _fact.value?.let { list.add(it) }
            saveFactsToStorageUseCase.execute(key = "KEY_FAVOURITE", facts = list)
        }
    }

    private fun saveHistoryFacts() {
        loadHistoryFacts().also { list ->
            fact.value?.let { list.add(it) }
            saveFactsToStorageUseCase.execute(key = "KEY_HISTORY", facts = list)
        }
    }

    private fun loadHistoryFacts() =
        mutableListOf<FactModel>().also {
            it.addAll(loadFactsFromStorageUseCase.execute(key = "KEY_HISTORY")
                .ifEmpty { mutableListOf<FactModel>() } as MutableList<FactModel>
            )
        }


    private fun loadFavouriteFacts() =
        mutableListOf<FactModel>().also {
            it.addAll(loadFactsFromStorageUseCase.execute(key = "KEY_FAVOURITE")
                .ifEmpty { mutableListOf<FactModel>() } as MutableList<FactModel>
            )
        }
}