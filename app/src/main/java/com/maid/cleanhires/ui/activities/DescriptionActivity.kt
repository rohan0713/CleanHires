package com.maid.cleanhires.ui.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.maid.cleanhires.R
import com.maid.cleanhires.data.models.Services
import com.maid.cleanhires.databinding.ActivityDescriptionBinding
import com.maid.cleanhires.network.RetrofitClient
import com.maid.cleanhires.ui.adapters.HiresAdapter
import com.maid.cleanhires.ui.adapters.ServiceAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DescriptionActivity : AppCompatActivity() {

    lateinit var binding: ActivityDescriptionBinding
    lateinit var serviceAdapter2: HiresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")
        val used = intent.getIntExtra("used", 0).toString()
        val category = intent.getStringExtra("category")

        binding.tvCategory.text = category
        binding.tvTitle.text = title
        binding.tvUsers.text = "${used}+ People Used"

        Picasso.get().load(url).into(binding.ivService)
        setupBookedRecyclerView()
//        showContent()
        window.statusBarColor = Color.WHITE

    }

    private fun setupBookedRecyclerView() {
        val list = listOf<Services>()
        serviceAdapter2 = HiresAdapter()
        binding.rvChefs.apply {
            adapter = serviceAdapter2
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

//    private fun showContent(){
//
//        lifecycleScope.launch(Dispatchers.IO) {
//            val response = RetrofitClient.api.getServices()
//            withContext(Dispatchers.Main){
//                if(response.isSuccessful){
//                    val list = response.body()!!
//                    serviceAdapter2 = ServiceAdapter(list)
//                    binding.rvChefs.adapter = serviceAdapter2
//                }
//            }
//        }
//    }
}