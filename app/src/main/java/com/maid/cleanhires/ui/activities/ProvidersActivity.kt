package com.maid.cleanhires.ui.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.maid.cleanhires.R
import com.maid.cleanhires.data.models.CategoriesItem
import com.maid.cleanhires.databinding.ActivityProvidersBinding
import com.maid.cleanhires.network.RetrofitClient
import com.maid.cleanhires.ui.adapters.HiresAdapter
import com.maid.cleanhires.ui.adapters.ProvidersAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProvidersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProvidersBinding
    lateinit var providerAdapter: ProvidersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityProvidersBinding.inflate(layoutInflater).also { binding = it }.root)

        window.statusBarColor = Color.WHITE
        setupRecyclerView()

        lifecycleScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.api.getCleanHiresWorkers()
            if(response.isSuccessful){
                withContext(Dispatchers.Main){
                    response.body()?.let {
                        binding.rvProviders.adapter = ProvidersAdapter(it.categories)
                    }
                }
            }
        }

    }

    private fun setupRecyclerView() {
        val list = listOf<CategoriesItem>()
        providerAdapter = ProvidersAdapter(list)
        binding.rvProviders.apply {
            adapter = providerAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
        }
    }
}