package com.maid.cleanhires.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityDetailsBinding
import com.maid.cleanhires.ui.adapters.ReviewsAdapter

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityDetailsBinding.inflate(layoutInflater).also { binding = it }.root)

        window.statusBarColor = Color.WHITE
        binding.rvReviews.layoutManager = LinearLayoutManager(this)
        binding.rvReviews.adapter = ReviewsAdapter()
    }
}