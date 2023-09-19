package com.maid.cleanhires.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityMainBinding
import com.maid.cleanhires.ui.activities.HomeActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed(
            Runnable {
                Intent(this@MainActivity, HomeActivity::class.java).also {
                    startActivity(it)
                }
            }
        , 4000)
    }
}