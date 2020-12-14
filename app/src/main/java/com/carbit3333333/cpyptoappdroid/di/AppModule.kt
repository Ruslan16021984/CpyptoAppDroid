package com.carbit3333333.cpyptoappdroid.di

import android.content.Context
import com.carbit3333333.cpyptoappdroid.mvp.presenters.CurrenciesPresenter
import com.carbit3333333.cpyptoappdroid.mvp.presenters.LatestChartPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app:App) {
    @Provides
    @Singleton
    fun provideContext():Context = app
}