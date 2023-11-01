package com.maid.cleanhires.ui.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityCartBinding
import com.maid.cleanhires.ui.adapters.CartItemAdapter

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityCartBinding.inflate(layoutInflater).also { binding = it }.root)

        window.statusBarColor = Color.WHITE
        binding.rvServices.layoutManager = LinearLayoutManager(this)
        binding.rvServices.adapter = CartItemAdapter()
    }
}