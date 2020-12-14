package com.carbit3333333.cpyptoappdroid.mvp.contract

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class BaseContract {
    interface View
    abstract class Presenter<V: View>{

        private val subscriptions = CompositeDisposable()
        protected lateinit var view: V

        fun subscribe(subscription: Disposable){
            subscriptions.add(subscription)
        }
        fun unsubscribe(){
            subscriptions.clear()
        }
        fun attach(view: V){
            this.view = view
        }
        fun detach(){
            unsubscribe()
        }
    }
}