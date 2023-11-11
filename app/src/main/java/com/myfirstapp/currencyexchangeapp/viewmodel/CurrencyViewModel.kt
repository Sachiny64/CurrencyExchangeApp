package com.myfirstapp.currencyexchangeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myfirstapp.currencyexchangeapp.repository.CurrencyRepository

class CurrencyViewModel: ViewModel() {
    var myRepository = CurrencyRepository()
    var isloading : MutableLiveData<Boolean> =myRepository.isLoading
    var isConverting :MutableLiveData<Boolean> = myRepository.isExchanging

    fun getCurrency(): MutableLiveData<List<String>>{
        return myRepository.getCurrencyList()
    }

    fun getExchangeData(from: String, to: String, quantity: Double) : MutableLiveData<Double>{
        return myRepository.exchangedCurrency(from, to, quantity)
    }
}