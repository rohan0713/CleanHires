package com.maid.cleanhires.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maid.cleanhires.data.models.ReviewsItem
import com.maid.cleanhires.databinding.ReviewsItemBinding
import com.maid.cleanhires.mercor.mData.models.ResultsItem

class ReviewsAdapter(
    val list: List<ReviewsItem>
) : RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {

    lateinit var binding: ReviewsItemBinding
    inner class ReviewViewHolder(itemview : ReviewsItemBinding) : RecyclerView.ViewHolder(itemview.root) {
        fun bind(resultsItem: ReviewsItem) {
            binding.tvUserName.text = resultsItem.username
            resultsItem.feedback?.let {
                binding.tvReview.text = it
            }
            binding.tvDate.text = resultsItem.timestamp
            resultsItem.rated?.let {
                binding.ratingBar.rating = resultsItem.rated
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        binding = ReviewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(list[position])
    }

}