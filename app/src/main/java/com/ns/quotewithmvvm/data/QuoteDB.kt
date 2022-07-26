package com.ns.quotewithmvvm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ns.quotewithmvvm.model.Quote

@Database(entities = [Quote::class], version = 1)
abstract class QuoteDB : RoomDatabase() {

    abstract fun quoteDao(): QuoteDAO

    companion object {
        private var instance: QuoteDB? = null

        fun getQuoteDatabase(context: Context): QuoteDB? {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    QuoteDB::class.java,
                    "quote.db"
                ).allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}