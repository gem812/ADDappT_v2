package com.example.addappt.data.quotes

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.addappt.data.quotes.entities.QuotesTable
import com.example.addappt.utils.DateTypeConverter

@Database(entities = [QuotesTable::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class QuotesDatabase : RoomDatabase() {

    abstract fun quotesDao() : QuotesDao

}