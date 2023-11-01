package com.maid.cleanhires.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maid.cleanhires.databinding.ProvidersItemBinding
import com.maid.cleanhires.ui.activities.DetailsActivity

class ProvidersAdapter : RecyclerView.Adapter<ProvidersAdapter.ProviderViewHolder>() {

    private lateinit var binding: ProvidersItemBinding
    inner class ProviderViewHolder(itemView: ProvidersItemBinding) : RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProviderViewHolder {
        binding = ProvidersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProviderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindViewHolder(holder: ProviderViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            Intent(binding.root.context, DetailsActivity::class.java).also {
                binding.root.context.startActivity(it)
            }
        }
    }

}