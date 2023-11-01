package com.maid.cleanhires.mercor.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityTaskBinding
import com.maid.cleanhires.mercor.adapters.CharacterAdapter
import com.maid.cleanhires.mercor.mData.models.ResultsItem
import com.maid.cleanhires.mercor.mData.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Image and Name of the character in a gridview of span length 2

class TaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityTaskBinding.inflate(layoutInflater).also { binding = it }.root)

        binding.rvCharacters.layoutManager = GridLayoutManager(this, 2);

        lifecycleScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.api.getResults()
            if(response.isSuccessful){
                    withContext(Dispatchers.Main){
                        response.body()?.let {
                        binding.rvCharacters.adapter = CharacterAdapter(it.results)
                            binding.rvCharacters.hasFixedSize()
                    }
                }
            }
        }
    }
}