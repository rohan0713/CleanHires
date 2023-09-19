package com.maid.cleanhires.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maid.cleanhires.data.models.Services
import com.maid.cleanhires.databinding.ServiceItemBinding
import com.squareup.picasso.Picasso

class ServiceAdapter(
    val list: List<Services>
) : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    lateinit var binding: ServiceItemBinding

    inner class ServiceViewHolder(itemView : ServiceItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        fun bind(services: Services) {
            binding.tvCategory.text = services.category
            binding.tvServiceName.text = services.title
            binding.tvUsers.text = "${services.used}+ People Used"
            Picasso.get().load(services.urlImage).into(binding.ivService)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        binding = ServiceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {

        holder.bind(list[position])
    }
}