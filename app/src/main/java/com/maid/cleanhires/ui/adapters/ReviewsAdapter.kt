package com.maid.cleanhires.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maid.cleanhires.databinding.ReviewsItemBinding

class ReviewsAdapter() : RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {

    lateinit var binding: ReviewsItemBinding
    inner class ReviewViewHolder(itemview : ReviewsItemBinding) : RecyclerView.ViewHolder(itemview.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        binding = ReviewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
    }

}