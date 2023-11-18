package com.maid.cleanhires.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maid.cleanhires.data.models.CartItems
import com.maid.cleanhires.databinding.CartItemBinding
import com.maid.cleanhires.ui.viewmodels.CartViewModel

class CartItemAdapter(
    private val cartItems: List<CartItems>,
val viewModel: CartViewModel) : RecyclerView.Adapter<CartItemAdapter.CartViewHolder>(){

    lateinit var binding: CartItemBinding
    inner class CartViewHolder(itemView : CartItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        fun bind(cartItems: CartItems) {
            binding.tvWorkerName.text = cartItems.name
            binding.tvServiceName.text = cartItems.item
            binding.tvBookingTime.text = cartItems.bookingTimeAndDate
            binding.tvAmount.text = "₹ ${cartItems.amount.toString()}"

            binding.ivDelete.setOnClickListener {
                viewModel.deleteFromCart(cartItems)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }

}