package com.maid.cleanhires.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "serviceTable")
data class Services(
    @PrimaryKey
    val title : String,
    val category : String,
    val used : Int,
    val Availability : Boolean,
    val urlImage : String
)