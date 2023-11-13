package com.maid.cleanhires.ui.fragments

import android.content.Intent
import com.maid.cleanhires.data.local.room.ServiceDatabase
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.maid.cleanhires.data.models.Services
import com.maid.cleanhires.databinding.FragmentServicesBinding
import com.maid.cleanhires.main.MyApplication
import com.maid.cleanhires.repositories.ServiceRepository
import com.maid.cleanhires.ui.activities.CartActivity
import com.maid.cleanhires.ui.activities.HomeActivity
import com.maid.cleanhires.ui.adapters.ServiceAdapter
import com.maid.cleanhires.ui.viewmodels.ServiceViewModel
import com.maid.cleanhires.ui.viewmodels.ViewModelProviderFactory
import com.maid.cleanhires.utils.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class ServicesFragment : Fragment() {

    lateinit var binding: FragmentServicesBinding
    lateinit var serviceAdapter: ServiceAdapter
    lateinit var serviceAdapter2: ServiceAdapter
    lateinit var viewModel : ServiceViewModel

//    private lateinit var viewModel : ServiceViewModel
//    @Inject
//    private lateinit var repository: ServiceRepository
//    private lateinit var application: MyApplication

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        application = requireActivity().applicationContext as MyApplication
//        repository = application.repository

        viewModel = (requireActivity() as HomeActivity).viewModel

//        val viewModelProvider = ViewModelProviderFactory(repository)
//
//        viewModel = ViewModelProvider(this, viewModelProvider).get(ServiceViewModel::class.java)

        viewModel.services.observe(viewLifecycleOwner) {
            useTheResponse(it)
        }

        binding.ivCart.setOnClickListener {
            Intent(binding.root.context, CartActivity::class.java).also { startActivity(it) }
        }
    }

    private fun useTheResponse(it: List<Services>) {
        serviceAdapter = ServiceAdapter(it)
        serviceAdapter2 = ServiceAdapter(it.subList(0, it.size/2))
        binding.rvServices.adapter = serviceAdapter
        binding.rvMostBooked.adapter = serviceAdapter2
        binding.clMainLayout.visibility = View.VISIBLE
        binding.relativeLayout.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        val list = listOf<Services>()
        serviceAdapter = ServiceAdapter(list)
        binding.rvServices.apply {
            adapter = serviceAdapter
            if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                layoutManager = GridLayoutManager(context, 2)
            }else{
                layoutManager = GridLayoutManager(context, 3)
            }
        }
    }

    private fun setupBookedRecyclerView() {
        val list = listOf<Services>()
        serviceAdapter2 = ServiceAdapter(list)
        binding.rvMostBooked.apply {
            adapter = serviceAdapter2
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}