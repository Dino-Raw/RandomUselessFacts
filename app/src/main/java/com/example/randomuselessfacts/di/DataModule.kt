package com.example.randomuselessfacts.di

import android.content.Context
import android.content.SharedPreferences
import com.example.data.repository.network.ApiFactsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module(includes = [DataBindModule::class])
class DataModule {
    @Singleton
    @Provides
    fun provideRetrofit(): ApiFactsService = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://uselessfacts.jsph.pl/")
        .build()
        .create(ApiFactsService::class.java)

    @Provides
    @Singleton
    fun provideSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences("KEY_STORAGE", Context.MODE_PRIVATE)
    }
}