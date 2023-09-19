package com.maid.cleanhires.ui.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityHomeBinding
import com.maid.cleanhires.ui.fragments.ProfileFragment
import com.maid.cleanhires.ui.fragments.ServicesFragment

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.WHITE
        loadFragment(ServicesFragment())

        binding.bottomNavigationView.setOnItemSelectedListener { 
            when (it.itemId){
                R.id.HomePage -> {
                    loadFragment(ServicesFragment())
                    true
                }
                else -> {
                    loadFragment(ProfileFragment())
                    true
                }
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFrameLayout,fragment)
        transaction.commit()
    }
}