package com.example.addappt.screens.splash

import androidx.lifecycle.ViewModel
import com.example.addappt.data.DataOrException
import com.example.addappt.models.data.quotes.Quotes
import com.example.addappt.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val repository: QuoteRepository) : ViewModel() {

    suspend fun getQuotes() : DataOrException<Quotes, Boolean, Exception>{
        return repository.getQuotes()
    }

}