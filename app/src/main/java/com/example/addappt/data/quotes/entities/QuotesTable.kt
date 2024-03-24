package com.example.addappt.data.quotes.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "quotes_table")
data class QuotesTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val quote: String = "",
    val author : String = "",
    val createdAt: Date = Date()
)
