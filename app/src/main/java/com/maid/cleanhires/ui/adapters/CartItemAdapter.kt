package com.maid.cleanhires.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maid.cleanhires.databinding.CartItemBinding

class CartItemAdapter : RecyclerView.Adapter<CartItemAdapter.CartViewHolder>(){

    lateinit var binding: CartItemBinding
    inner class CartViewHolder(itemView : CartItemBinding) : RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
    }

}