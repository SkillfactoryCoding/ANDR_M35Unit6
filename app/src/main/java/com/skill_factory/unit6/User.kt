package com.skill_factory.unit6

import androidx.databinding.ObservableField

class User(_cash: Int, _stockList: ArrayList<Stock>) {
    val cashObservable = ObservableField(_cash)
    var cash: Int = _cash
    set(value) {
        field = value
        cashObservable.set(value)
    }

    val stockListObservable = ObservableField(_stockList)
    var stockList: ArrayList<Stock> = _stockList
        set(value) {
            field = value
            stockListObservable.set(value)
        }
}
