package com.ns.quotewithmvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote")
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val q: String,
    val a: String
) {
}