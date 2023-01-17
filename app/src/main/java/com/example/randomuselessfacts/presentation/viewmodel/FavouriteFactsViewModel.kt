package com.example.randomuselessfacts.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.domian.model.FactModel
import com.example.domian.usecase.LoadFactsFromStorageUseCase
import com.example.domian.usecase.RemoveFactsFromStorageUseCase
import com.example.domian.usecase.SaveFactsToStorageUseCase
import com.example.randomuselessfacts.presentation.SwipeToDeleteCallback
import com.example.randomuselessfacts.presentation.adapter.FavouriteFactsAdapter
import javax.inject.Inject

class FavouriteFactsViewModel @Inject constructor(
    private val loadFactsFromStorageUseCase: LoadFactsFromStorageUseCase,
    private val removeFactsFromStorageUseCase: RemoveFactsFromStorageUseCase,
    private val saveFactsToStorageUseCase: SaveFactsToStorageUseCase,
    val adapter: FavouriteFactsAdapter,
): ViewModel() {
    private val _factListFavourite: MutableLiveData<MutableList<FactModel>> =
        MutableLiveData(mutableListOf<FactModel>())
    val factListFavourite: LiveData<MutableList<FactModel>> = _factListFavourite

    init {
        loadFavouriteFacts()
    }

    fun setAdapter() {
        adapter.setData(_factListFavourite.value as ArrayList<FactModel>)
    }

    fun removeFavouriteFacts() {
        removeFactsFromStorageUseCase.execute(key = "KEY_FAVOURITE")
        _factListFavourite.value?.clear()
        setAdapter()
        saveFavouriteFacts()
    }

    fun removeFavouriteFact(position: Int) {
        _factListFavourite.value?.removeAt(position)
        adapter.removeItem(position)
        saveFavouriteFacts()
    }

    private fun loadFavouriteFacts() {
        mutableListOf<FactModel>().also { list ->
            list.addAll(
                loadFactsFromStorageUseCase.execute(key = "KEY_FAVOURITE")
                    .ifEmpty { mutableListOf<FactModel>() } as MutableList<FactModel>
            )
            _factListFavourite.value = list
        }
    }

    private fun saveFavouriteFacts() {
        _factListFavourite.value?.let { facts ->
            saveFactsToStorageUseCase.execute(key = "KEY_FAVOURITE", facts = facts)
        }
    }
}