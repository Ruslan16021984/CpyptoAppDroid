package com.carbit3333333.cpyptoappdroid.di

import com.carbit3333333.cpyptoappdroid.MainActivity
import com.carbit3333333.cpyptoappdroid.actovitys.ActivityChart
import com.carbit3333333.cpyptoappdroid.chart.LatestChart
import com.carbit3333333.cpyptoappdroid.fragments.CurrenciesListFragment
import com.carbit3333333.cpyptoappdroid.mvp.presenters.CurrenciesPresenter
import com.carbit3333333.cpyptoappdroid.mvp.presenters.LatestChartPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class,RestModule::class, MvpModule::class, ChartModule::class))
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(presenter: CurrenciesPresenter)
    fun inject(presenter: LatestChartPresenter)
    fun inject(fragment: CurrenciesListFragment)
    fun inject(latestChart: LatestChart)
    fun inject(activityChart: ActivityChart)
}