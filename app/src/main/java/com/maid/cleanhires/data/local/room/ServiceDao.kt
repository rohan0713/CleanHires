package com.maid.cleanhires.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.maid.cleanhires.data.models.Services
import kotlinx.coroutines.flow.StateFlow

@Dao
interface ServiceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(service: Services)

    @Query("Select * from serviceTable")
    fun getServices(): List<Services>

}