package com.ns.quotewithmvvm.data

import androidx.room.*
import com.ns.quotewithmvvm.model.Quote

@Dao
interface QuoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(quote: Quote)

    @Delete
    fun delete(quote: Quote)

    @Query("Select * FROM quote")
    fun getAllQuote(): List<Quote>

    @Query("DELETE FROM quote")
    fun deleteAllQuote()
}