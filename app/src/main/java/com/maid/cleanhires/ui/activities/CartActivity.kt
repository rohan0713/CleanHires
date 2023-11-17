package com.maid.cleanhires.ui.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.maid.cleanhires.databinding.ActivityCartBinding
import com.maid.cleanhires.repositories.CartRepository
import com.maid.cleanhires.ui.adapters.CartItemAdapter
import com.maid.cleanhires.ui.viewmodels.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    val viewModel :CartViewModel by viewModels()
    @Inject
    lateinit var repository: CartRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityCartBinding.inflate(layoutInflater).also { binding = it }.root)

        window.statusBarColor = Color.WHITE
        binding.rvServices.layoutManager = LinearLayoutManager(this)

        viewModel.items.observe(this){
            binding.rvServices.adapter = CartItemAdapter(it)
        }
    }
}