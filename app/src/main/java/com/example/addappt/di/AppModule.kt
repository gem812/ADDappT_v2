package com.example.addappt.di

import android.content.Context
import androidx.room.Room
import com.example.addappt.Constants.QuoteApi.BASE_URL
import com.example.addappt.data.quotes.QuotesDao
import com.example.addappt.data.quotes.QuotesDatabase
import com.example.addappt.network.QuoteAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideQuotesDao(quotesDatabase: QuotesDatabase): QuotesDao = quotesDatabase.quotesDao()

    @Singleton
    @Provides
    fun providesQuoteData(@ApplicationContext context: Context): QuotesDatabase =
        Room.databaseBuilder(
            context,
            QuotesDatabase::class.java,
            "quotes_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideQuotesAPI() : QuoteAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteAPI::class.java)
    }

}