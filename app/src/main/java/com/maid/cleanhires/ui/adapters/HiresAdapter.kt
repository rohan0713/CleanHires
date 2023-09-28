package com.maid.cleanhires.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maid.cleanhires.databinding.EmployeeItemBinding

class HiresAdapter(

) : RecyclerView.Adapter<HiresAdapter.HireViewHolder>(){

    lateinit var binding: EmployeeItemBinding

    inner class HireViewHolder(itemview: EmployeeItemBinding) : RecyclerView.ViewHolder(itemview.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HireViewHolder {
        binding = EmployeeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HireViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: HireViewHolder, position: Int) {
    }
}