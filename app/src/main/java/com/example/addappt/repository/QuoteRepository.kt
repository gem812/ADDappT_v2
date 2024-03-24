package com.example.addappt.repository

import android.util.Log
import com.example.addappt.data.DataOrException
import com.example.addappt.models.data.quotes.Quotes
import com.example.addappt.network.QuoteAPI
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val api : QuoteAPI) {

    suspend fun getQuotes() : DataOrException<Quotes, Boolean, Exception>{

        val response = try {
            api.getQuotes("quotes")
        } catch(e : Exception){
            Log.d("QuoteRepo", "FAILURE!!!")
            return DataOrException(e = e)
        }
        Log.d("QuoteRepo", "SUCCESS!! ${response.quotes[0].q}")
        return DataOrException(data = response)
    }

}