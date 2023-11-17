package com.maid.cleanhires.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maid.cleanhires.data.models.CartItems
import com.maid.cleanhires.repositories.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    val items : LiveData<List<CartItems>> = repository.getCartItems()

    fun insertIntoCart(items: CartItems) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertIntoCart(items)
    }
}