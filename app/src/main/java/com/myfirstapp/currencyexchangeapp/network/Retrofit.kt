package com.myfirstapp.currencyexchangeapp.network

import com.google.gson.Gson
import com.myfirstapp.currencyexchangeapp.methods.ApiMethods
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
//    var apiKey = "b4041c4535mshcbb8e8b2795a8a8p1b2e92jsnd8cce635fce7"
//    var host = "currency-exchange.p.rapidapi.com"
    private val retrofitClient: Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl("https://currency-exchange.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
    }

    val apiCall: ApiMethods by lazy {
        retrofitClient.build().create(ApiMethods::class.java)
    }

}