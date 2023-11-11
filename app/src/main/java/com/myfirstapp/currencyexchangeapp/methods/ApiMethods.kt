package com.myfirstapp.currencyexchangeapp.methods

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiMethods {
    @Headers("X-RapidAPI-Key: b4041c4535mshcbb8e8b2795a8a8p1b2e92jsnd8cce635fce7", "X-RapidAPI-Host: currency-exchange.p.rapidapi.com")
    @GET("listquotes")
    fun getCurrencyList() : retrofit2.Call<List<String>>


    @Headers("X-RapidAPI-Key: b4041c4535mshcbb8e8b2795a8a8p1b2e92jsnd8cce635fce7", "X-RapidAPI-Host: currency-exchange.p.rapidapi.com")
    @GET("exchange")
    fun getConvertResult(
        @Query("from") from :String,
        @Query("to") to:String,
        @Query("q") quantity:Double) : retrofit2.Call<Double>
}
