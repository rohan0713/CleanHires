package com.maid.cleanhires.ui.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityHomeBinding
import com.maid.cleanhires.ui.fragments.ProfileFragment
import com.maid.cleanhires.ui.fragments.ServicesFragment
import com.maid.cleanhires.ui.viewmodels.ServiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Time
import java.util.TimeZone
import java.util.Timer
import java.util.concurrent.TimeUnit
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    var pressedTime : Long? = 0
    val viewModel : ServiceViewModel by viewModels()
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

    override fun onBackPressed() {
        if(pressedTime?.plus(1000)!! >= System.currentTimeMillis()){
            super.onBackPressed()
        }else{
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
        pressedTime = System.currentTimeMillis()
    }
}