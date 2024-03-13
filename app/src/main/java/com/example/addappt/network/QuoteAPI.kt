package com.example.addappt.network

import com.example.addappt.models.data.quotes.Quotes
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteAPI {
    @GET()
    suspend fun getQuotes(
        @Query("api") api : String = "quotes"
    ) : Quotes

}