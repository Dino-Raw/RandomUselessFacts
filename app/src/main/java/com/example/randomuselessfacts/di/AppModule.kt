package com.example.randomuselessfacts.di

import android.content.Context
import com.example.randomuselessfacts.presentation.activity.MainActivity
import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class])
class AppModule(val context: Context) {
    @Provides
    fun provideContext() = context
}