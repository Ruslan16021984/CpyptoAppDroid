package com.carbit3333333.cpyptoappdroid.mvp.presenters

import com.carbit3333333.cpyptoappdroid.di.App
import com.carbit3333333.cpyptoappdroid.mvp.contract.LatestChartContract
import com.carbit3333333.cpyptoappdroid.rest.CoinGeckoApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LatestChartPresenter: LatestChartContract.Presenter() {
    @Inject
    lateinit var geckoApi: CoinGeckoApi
    init {
        App.appComponent.inject(this)
    }
    override fun makeChart(id: String) {
        subscribe(geckoApi.getCoinMarketChart(id)
//                Единственное отличие — используется
//                оператор map вместо flatMap, поскольку функция geckoApi.getCoinMarketChart(id)
//        возвращает не список, а Observable<GeckoCoinChart>, и нам не нужно его разворачивать.
            .map { it.prices }
            .flatMap { Observable.fromIterable(it) }
            .doOnComplete{
                view.hideProgress()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                view.addEntryToChart(it[0], it[1])
            },{
                view.hideProgress()
                view.showErrorMessage(it.message)
                it.printStackTrace()
            }))
    }

    override fun refreshChart() {
        view.refresh()
    }
}