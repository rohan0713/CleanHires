package com.maid.cleanhires.ui.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Display.Mode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.FragmentProfileBinding
import com.maid.cleanhires.databinding.FragmentServicesBinding
import com.maid.cleanhires.ui.activities.CartActivity
import com.maid.cleanhires.ui.activities.LoginActivity
import com.maid.cleanhires.ui.activities.LoginFirstActivity
import com.maid.cleanhires.ui.activities.UpdateProfile
import com.maid.cleanhires.ui.adapters.ServiceAdapter

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = binding.root.context.getSharedPreferences("data", Context.MODE_PRIVATE)

        binding.logout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            Intent(binding.root.context, LoginFirstActivity::class.java).also {
                startActivity(it)
            }
            requireActivity().finish()
        }

        binding.bookings.setOnClickListener {
            Intent(binding.root.context, CartActivity::class.java).also { startActivity(it) }
        }

        binding.myProfile.setOnClickListener {
            Intent(binding.root.context, UpdateProfile::class.java).also { startActivity(it) }
        }

    }
}