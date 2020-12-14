package com.carbit3333333.cpyptoappdroid.di

import com.carbit3333333.cpyptoappdroid.YearValueFormatter
import com.carbit3333333.cpyptoappdroid.chart.LatestChart
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ChartModule {
    @Provides
    @Singleton
    fun provideLatestChart() = LatestChart()

    @Provides
    @Singleton
    fun provideYearFormatter() = YearValueFormatter()
}