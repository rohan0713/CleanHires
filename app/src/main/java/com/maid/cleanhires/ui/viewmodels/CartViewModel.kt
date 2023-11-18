package com.maid.cleanhires.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

//    val flag: MutableLiveData<Boolean> by lazy {
//        MutableLiveData<Boolean>()
//    }
//
//    val discountedAmount : MutableLiveData<Int> by lazy {
//        MutableLiveData<Int>()
//    }

    val items : LiveData<List<CartItems>> = repository.getCartItems()
    val totalAmount : LiveData<Int> = repository.getAmount()
//    val totalAmount : LiveData<Int> = if(flag.value == false) { repository.getAmount() } else { (LiveData<Int>())(repository.getAmount().value - repository.getAmount().value/10)}

    fun insertIntoCart(items: CartItems) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertIntoCart(items)
    }

    fun deleteFromCart(items: CartItems) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteItem(items)
    }

//    fun updateFlag(newValue : Boolean) {
//        flag.value = newValue
//    }
//
//    fun updateValue(newValue: Int) : LiveData<Int>{
//        discountedAmount.value = newValue
//        return discountedAmount
//    }

}