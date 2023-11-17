package com.maid.cleanhires.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.maid.cleanhires.data.models.CartItems
import com.maid.cleanhires.data.models.Services
import kotlinx.coroutines.flow.StateFlow

@Dao
interface ServiceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(service: Services)

    @Query("Select * from serviceTable")
    fun getServices(): List<Services>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoCart(item: CartItems)

    @Query("Select * from cart")
    fun getCartItems() : LiveData<List<CartItems>>

    @Query("Select * from cart where item = :title")
    fun getItemAmount(title : String) : List<CartItems>

    @Delete
    fun deleteCartItem(items: CartItems)

}