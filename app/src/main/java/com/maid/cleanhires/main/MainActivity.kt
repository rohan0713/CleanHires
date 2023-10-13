package com.maid.cleanhires.main

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityMainBinding
import com.maid.cleanhires.ui.activities.HomeActivity
import com.maid.cleanhires.ui.activities.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.statusBarColor = Color.TRANSPARENT

        Handler(Looper.getMainLooper()).postDelayed(
            Runnable {
                Intent(this@MainActivity, LoginActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        , 2000)
    }
}