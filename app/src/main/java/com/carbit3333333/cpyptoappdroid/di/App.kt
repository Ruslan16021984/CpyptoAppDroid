package com.carbit3333333.cpyptoappdroid.di

import android.app.Application

class App: Application() {
    companion object{
        lateinit var appComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()
        initializerDagger()

    }
    private fun initializerDagger(){
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this))
            .restModule(RestModule()).mvpModule(MvpModule()).chartModule(ChartModule()).build()
    }
}