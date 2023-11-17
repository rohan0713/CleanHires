package com.maid.cleanhires.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.maid.cleanhires.R
import com.maid.cleanhires.data.models.CategoriesItem
import com.maid.cleanhires.data.models.Services
import com.maid.cleanhires.data.models.WorkersResponse
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
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)

        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")
        val used = intent.getIntExtra("used", 0).toString()
        val category = intent.getStringExtra("category")
        val desc = intent.getStringExtra("desc")

        val editor = sharedPreferences.edit()
        editor.putString("title", title.toString())
        editor.apply()

        binding.tvCategory.text = category
        binding.tvTitle.text = title
        binding.tvUsers.text = "${used}+ People Used"
        binding.tvDescription.text = desc

//        Picasso.get().load(url).into(binding.ivService)

        Glide.with(this).load(url).into(binding.ivService)

        setupBookedRecyclerView()
//        showContent()
        window.statusBarColor = Color.WHITE

        lifecycleScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.api.getCleanHiresWorkers()
            if(response.isSuccessful){
                withContext(Dispatchers.Main){
                   response.body()?.let {
                       binding.rvChefs.adapter = HiresAdapter(it.categories)
                   }
                }
            }
        }

        binding.btnBook.setOnClickListener {
            Intent(this, ProvidersActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    private fun setupBookedRecyclerView() {
        val list = listOf<CategoriesItem>()
        serviceAdapter2 = HiresAdapter(list)
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