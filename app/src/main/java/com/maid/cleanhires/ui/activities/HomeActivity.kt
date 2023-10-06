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

        if(savedInstanceState == null) {
            loadFragment(ServicesFragment())
        }

        val fragmentMap = mapOf(
            R.id.HomePage to ServicesFragment(),
            R.id.ProfilePage to ProfileFragment()
        )

        binding.bottomNavigationView.setOnItemSelectedListener { 

            fragmentMap[it.itemId]?.let {
                fragment -> loadFragment(fragment)
                true
            } ?: false
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFrameLayout,fragment)
        transaction.commit()
    }
}