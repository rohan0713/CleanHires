package com.maid.cleanhires.data.models

import androidx.room.Entity

@Entity(tableName = "serviceTable")
data class Services(
    val title : String,
    val category : String,
    val used : Int,
    val Availability : Boolean,
    val urlImage : String
)