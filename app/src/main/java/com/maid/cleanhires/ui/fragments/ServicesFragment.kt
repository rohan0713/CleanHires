package com.maid.cleanhires.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.FragmentServicesBinding

class ServicesFragment : Fragment() {

    lateinit var binding: FragmentServicesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentServicesBinding.inflate(layoutInflater)
        return binding.root
    }
}