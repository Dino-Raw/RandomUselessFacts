package com.example.randomuselessfacts.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domian.model.FactModel
import com.example.domian.usecase.LoadFactsFromStorageUseCase
import com.example.domian.usecase.RemoveFactsFromStorageUseCase
import com.example.randomuselessfacts.presentation.adapter.HistoryFactsAdapter
import javax.inject.Inject

class HistoryFactsViewModel @Inject constructor(
    private val loadFactsFromStorageUseCase: LoadFactsFromStorageUseCase,
    private val removeFactsFromStorageUseCase: RemoveFactsFromStorageUseCase,
    val adapter: HistoryFactsAdapter,
): ViewModel() {
    private val _factListHistory: MutableLiveData<MutableList<FactModel>> =
        MutableLiveData(mutableListOf<FactModel>())
    val factListHistory: LiveData<MutableList<FactModel>> = _factListHistory

    init {
        loadHistoryFacts()
    }

    fun removeHistoryFacts() {
        removeFactsFromStorageUseCase.execute(key = "KEY_HISTORY")
        _factListHistory.value?.clear()
        setAdapter()
    }

    private fun loadHistoryFacts() {
        mutableListOf<FactModel>().also { list ->
            list.addAll(
                loadFactsFromStorageUseCase.execute(key = "KEY_HISTORY")
                    .ifEmpty { mutableListOf<FactModel>() } as MutableList<FactModel>
            )
            _factListHistory.value = list
        }
    }

    fun setAdapter() {
        adapter.setData(_factListHistory.value as ArrayList<FactModel>)
    }
}