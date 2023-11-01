package com.maid.cleanhires.ui.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityProvidersBinding
import com.maid.cleanhires.ui.adapters.ProvidersAdapter

class ProvidersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProvidersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityProvidersBinding.inflate(layoutInflater).also { binding = it }.root)

        window.statusBarColor = Color.WHITE
        binding.rvProviders.layoutManager = LinearLayoutManager(this)
        binding.rvProviders.adapter = ProvidersAdapter()

    }
}