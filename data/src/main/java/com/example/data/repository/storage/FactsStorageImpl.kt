package com.example.data.repository.storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.data.model.FactStorageModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type
import javax.inject.Inject

class FactsStorageImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
    ): FactsStorage {
    override fun saveFacts(key: String, facts: List<FactStorageModel>) {
        val json = getJsonAdapter().toJson(facts)
        sharedPreferences
            .edit()
            .putString(key, json)
            .apply()
    }

    override fun loadFacts(key: String): List<FactStorageModel> {
        val json = sharedPreferences.getString(key, null) ?: return listOf()
        return getJsonAdapter().fromJson(json) ?: listOf()
    }

    override fun removeFacts(key: String) {
        sharedPreferences
            .edit()
            .remove(key)
            .apply()
    }

    private fun getJsonAdapter(): JsonAdapter<List<FactStorageModel>> {
        val moshi = Moshi.Builder().build()
        val type: Type = Types.newParameterizedType(List::class.java, FactStorageModel::class.java)
        return moshi.adapter(type)
    }
}