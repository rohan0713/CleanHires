package com.maid.cleanhires.ui.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maid.cleanhires.data.models.Services
import com.maid.cleanhires.databinding.ServiceItemBinding
import com.maid.cleanhires.ui.activities.DescriptionActivity
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
//            Picasso.get().load(services.urlImage).into(binding.ivService)

            Glide.with(binding.root).load(services.urlImage).into(binding.ivService)

            itemView.setOnClickListener { v ->
                Intent(v.context, DescriptionActivity::class.java).also {
                    it.putExtra("url", services.urlImage)
                    Log.d("url", services.urlImage)
                    it.putExtra("category", services.category)
                    it.putExtra("title", services.title)
                    it.putExtra("used", services.used)
                    v.context.startActivity(it)
                }
            }
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