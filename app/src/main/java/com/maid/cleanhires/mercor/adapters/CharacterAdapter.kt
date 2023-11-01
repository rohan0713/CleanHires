package com.maid.cleanhires.mercor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maid.cleanhires.databinding.CharacterViewBinding
import com.maid.cleanhires.mercor.mData.models.ResultsItem

class CharacterAdapter(
    private val list: List<ResultsItem>
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private lateinit var binding: CharacterViewBinding
    inner class CharacterViewHolder(itemView : CharacterViewBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(resultsItem: ResultsItem) {
            binding.tvCharacterName.text = resultsItem.name
            Glide.with(binding.root).load(resultsItem.image).into(binding.ivCharacter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        binding = CharacterViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(list[position])
    }

}