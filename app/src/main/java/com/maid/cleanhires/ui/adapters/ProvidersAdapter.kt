package com.maid.cleanhires.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maid.cleanhires.data.models.CategoriesItem
import com.maid.cleanhires.databinding.ProvidersItemBinding
import com.maid.cleanhires.ui.activities.DetailsActivity

class ProvidersAdapter(
    val list: List<CategoriesItem>
) : RecyclerView.Adapter<ProvidersAdapter.ProviderViewHolder>() {

    private lateinit var binding: ProvidersItemBinding
    inner class ProviderViewHolder(itemView: ProvidersItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        fun bind(worker: CategoriesItem) {
            binding.tvWorkerName.text = worker.name
            binding.tvWorkerLocation.text = worker.location
            binding.ratingBar.rating = worker.rating!!
            binding.tvBooking.text = "Completed ${worker.booking_count.toString()} Bookings"
            Glide.with(binding.root).load(worker.urlToImage).into(binding.ivService)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProviderViewHolder {
        binding = ProvidersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProviderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProviderViewHolder, position: Int) {

        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            Intent(binding.root.context, DetailsActivity::class.java).also {
                binding.root.context.startActivity(it)
            }
        }
    }

}