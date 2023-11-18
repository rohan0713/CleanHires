package com.maid.cleanhires.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "cart")
data class CartItems(
    val name : String,
    val item : String,
    val bookingTimeAndDate : String,
    val amount : Int,

    @PrimaryKey
    val id : String = UUID.randomUUID().toString()
)