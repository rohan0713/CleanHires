package com.maid.cleanhires.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.maid.cleanhires.R
import com.maid.cleanhires.data.models.Services
import com.maid.cleanhires.databinding.FragmentServicesBinding
import com.maid.cleanhires.network.RetrofitClient
import com.maid.cleanhires.ui.adapters.ServiceAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ServicesFragment : Fragment() {

    lateinit var binding: FragmentServicesBinding
    lateinit var serviceAdapter: ServiceAdapter
    lateinit var serviceAdapter2: ServiceAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentServicesBinding.inflate(layoutInflater)
        setupRecyclerView()
        setupBookedRecyclerView()
        return binding.root
    }

    private fun setupBookedRecyclerView() {
        val list = listOf<Services>()
        serviceAdapter2 = ServiceAdapter(list)
        binding.rvMostBooked.apply {
            adapter = serviceAdapter2
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.api.getServices()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    val list = response.body()!!
                    serviceAdapter = ServiceAdapter(list)
                    serviceAdapter2 = ServiceAdapter(list)
                    binding.rvServices.adapter = serviceAdapter
                    binding.rvMostBooked.adapter = serviceAdapter2
                    binding.clMainLayout.visibility = View.VISIBLE
                    binding.relativeLayout.visibility = View.GONE
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val list = listOf<Services>()
        serviceAdapter = ServiceAdapter(list)
        binding.rvServices.apply {
            adapter = serviceAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }
}