package com.maid.cleanhires.ui.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityUpdateProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateProfile : AppCompatActivity() {

    lateinit var binding: ActivityUpdateProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityUpdateProfileBinding.inflate(layoutInflater).also { binding = it }.root)
        window.statusBarColor = Color.WHITE
    }
}