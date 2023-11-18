package com.maid.cleanhires.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityCartBinding
import com.maid.cleanhires.repositories.CartRepository
import com.maid.cleanhires.ui.adapters.CartItemAdapter
import com.maid.cleanhires.ui.viewmodels.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private val viewModel :CartViewModel by viewModels()
    @Inject
    lateinit var repository: CartRepository
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityCartBinding.inflate(layoutInflater).also { binding = it }.root)

        window.statusBarColor = Color.WHITE
        binding.rvServices.layoutManager = LinearLayoutManager(this)

        viewModel.items.observe(this){
            if(it.isNotEmpty()) {
                binding.rvServices.visibility = View.VISIBLE
                binding.tvItems.visibility = View.GONE
                binding.rvServices.adapter = CartItemAdapter(it, viewModel)
            }else{
                binding.rvServices.visibility = View.GONE
                binding.tvItems.visibility = View.VISIBLE
            }
        }

        binding.ivBack.setOnClickListener {
            finish();
        }

        binding.tvApplyCoupon.setOnClickListener {
            binding.tvApplyCoupon.apply {
                text = "Applied"
                setTextColor(getColor(R.color.applied))
            }
            binding.ivRemoveCoupon.visibility = View.VISIBLE
        }

        binding.ivRemoveCoupon.setOnClickListener {
            binding.tvApplyCoupon.apply {
                text = "Apply"
                setTextColor(getColor(R.color.base))
            }
            binding.ivRemoveCoupon.visibility = View.INVISIBLE
        }

        viewModel.totalAmount.observe(this) {

            if(it != null ){
                binding.tvTotal.text = "₹ ${it.toString()}"
                binding.tvToPay.text = "₹ ${it.toString()}"
                binding.tvTotalAmount.text = "Total Amount:\n₹${it.toString()}"
            }else{
                binding.tvTotal.text = "₹ 0"
                binding.tvToPay.text = "₹ 0"
                binding.tvTotalAmount.text = "Total Amount:\n₹0"
            }
        }

        binding.layoutBookMore.setOnClickListener {
            Intent(this@CartActivity, HomeActivity::class.java).also {
                startActivity(it)
                finish()
            }
            finish()
        }
    }
}