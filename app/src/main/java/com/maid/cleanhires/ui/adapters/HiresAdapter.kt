package com.maid.cleanhires.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maid.cleanhires.data.models.CategoriesItem
import com.maid.cleanhires.databinding.EmployeeItemBinding
import com.maid.cleanhires.ui.activities.DetailsActivity

class HiresAdapter(
    val list: List<CategoriesItem>
) : RecyclerView.Adapter<HiresAdapter.HireViewHolder>(){

    lateinit var binding: EmployeeItemBinding

    inner class HireViewHolder(itemview: EmployeeItemBinding) : RecyclerView.ViewHolder(itemview.root) {
        fun Bind(worker: CategoriesItem) {
            binding.tvWorkerName.text = worker.name
            binding.tvWorkerLocation.text = worker.location
            binding.ratingBar.rating = worker.rating!!

            worker.urlToImage?.let {
                Glide.with(binding.root).load(it).into(binding.ivService)
            }

            itemView.setOnClickListener {
                Intent(binding.root.context, DetailsActivity::class.java).also {
                    it.putExtra("name", worker.name)
                    it.putExtra("location", worker.location)
                    it.putExtra("rating", worker.rating)
                    it.putExtra("charges", worker.charges.toString())
                    it.putExtra("count", worker.booking_count.toString())
                    it.putExtra("duration", worker.duration.toString())
                    it.putExtra("joined", worker.joined)
                    it.putExtra("image", worker.urlToImage)
                    binding.root.context.startActivity(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HireViewHolder {
        binding = EmployeeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HireViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HireViewHolder, position: Int) {

        holder.Bind(list[position])

//        holder.itemView.setOnClickListener {
//            Intent(binding.root.context, DetailsActivity::class.java).also {
//                binding.root.context.startActivity(it)
//            }
//        }
    }
}