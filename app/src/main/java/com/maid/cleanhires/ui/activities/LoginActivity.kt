package com.maid.cleanhires.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
        window.statusBarColor = Color.TRANSPARENT
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
        val access = sharedPreferences.getBoolean("access", false)

        binding.materialButton.setOnClickListener {

            if(access) {
                Intent(this@LoginActivity, HomeActivity::class.java).also {
                    startActivity(it)
                }
            } else{
                Intent(this@LoginActivity, LoginFirstActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}