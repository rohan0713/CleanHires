package com.maid.cleanhires.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "cart")
data class CartItems(
    val item : String,
    val bookingTimeAndDate : String,

    @PrimaryKey
    val id : String = UUID.randomUUID().toString()
)