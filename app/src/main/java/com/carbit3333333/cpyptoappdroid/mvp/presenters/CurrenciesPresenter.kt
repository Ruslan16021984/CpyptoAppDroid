package com.carbit3333333.cpyptoappdroid.mvp.presenters

import com.carbit3333333.cpyptoappdroid.adapter.CurrenciesAdapter
import com.carbit3333333.cpyptoappdroid.di.App
import com.carbit3333333.cpyptoappdroid.formatThousands
import com.carbit3333333.cpyptoappdroid.mvp.contract.CurrenciesContract
import com.carbit3333333.cpyptoappdroid.rest.CoinGeckoApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CurrenciesPresenter : CurrenciesContract.Presenter() {
    //внедряем источник данных
    @Inject
    lateinit var geckoApi: CoinGeckoApi

    init {
        //инициализируем компоненты Даггера
        App.appComponent.inject(this)
    }
    //создаем список, загружая данные с помощью RxJava
    override fun makeList() {
        view.showProgress()
        //подписываемся на поток данных
        subscribe(geckoApi.getCoinMarket()
            //определяем отдельный поток для отправки данных
            .subscribeOn(Schedulers.io())
            //получаем данные в основном потоке
            .observeOn(AndroidSchedulers.mainThread())
            //преобразуем List<GeckoCoin> в Observable<GeckoCoin>
            .flatMap { Observable.fromIterable(it) }
            //наполняем поля элемента списка для адаптера
            .doOnNext {
                view.addCurrency(
                    CurrenciesAdapter.Currency(
                        it.id,
                        it.symbol,
                        it.name,
                        it.image,
                        it.current_price,
                        it.market_cap.formatThousands(),
                        it.market_cap_rank,
                        it.total_volume,
                        it.price_change_percentage_24h,
                        it.market_cap_change_percentage_24h,
                        it.circulating_supply,
                        it.total_supply,
                        it.ath,
                        it.ath_change_percentage
                    )
                )
                //вызывается при вызове onComplete
            }.doOnComplete {
                view.hideProgress()
                //подписывает Observer на Observable
            }.subscribe({
                view.hideProgress()
                view.notifyAdapter()
            }, {
                view.showErrorMessage(it.message)
                view.hideProgress()
                it.printStackTrace()
            })
        )
    }

    override fun refreshList() {
        view.refresh()
        makeList()
    }
}