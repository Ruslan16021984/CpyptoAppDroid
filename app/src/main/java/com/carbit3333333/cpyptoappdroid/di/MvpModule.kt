package com.carbit3333333.cpyptoappdroid.di

import com.carbit3333333.cpyptoappdroid.mvp.presenters.CurrenciesPresenter
import com.carbit3333333.cpyptoappdroid.mvp.presenters.LatestChartPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule {

    @Provides
    @Singleton
    fun provideCurrenciesPresenter() = CurrenciesPresenter()

    @Provides
    @Singleton
    fun provideChartPresenter() = LatestChartPresenter()
}