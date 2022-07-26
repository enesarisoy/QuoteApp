package com.ns.quotewithmvvm.service

import com.ns.quotewithmvvm.model.Quote
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface QuoteApi {

    @GET("quotes")
    fun getQuotes(): Call<List<Quote>>

}