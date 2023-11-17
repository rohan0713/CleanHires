package com.maid.cleanhires.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.maid.cleanhires.data.local.room.ServiceDatabase
import com.maid.cleanhires.data.models.CartItems
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val db: ServiceDatabase
) {

    suspend fun insertIntoCart(items: CartItems) = db.getDao().insertIntoCart(items)

    fun getCartItems() : LiveData<List<CartItems>> = db.getDao().getCartItems()

    fun getAmount(title : String) = db.getDao().getItemAmount(title)

    fun deleteItem(items: CartItems) = db.getDao().deleteCartItem(items)
}