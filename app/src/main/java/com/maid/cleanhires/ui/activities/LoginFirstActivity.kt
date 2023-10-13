package com.maid.cleanhires.ui.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityLoginFirstBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
@AndroidEntryPoint
class LoginFirstActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityLoginFirstBinding.inflate(layoutInflater).also { binding = it }.root)
        window.statusBarColor = Color.WHITE

        binding.btnLogin.setOnClickListener {
            Intent(this@LoginFirstActivity, HomeActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}