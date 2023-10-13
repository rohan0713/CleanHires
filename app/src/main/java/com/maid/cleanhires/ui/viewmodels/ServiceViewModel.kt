package com.maid.cleanhires.ui.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maid.cleanhires.data.models.Services
import com.maid.cleanhires.repositories.ServiceRepository
import com.maid.cleanhires.utils.NetworkConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val repository: ServiceRepository
) : ViewModel() {

    private val _services = MutableLiveData<List<Services>>()
    val services: LiveData<List<Services>> = _services

    init {
        getAllServices()
        Log.d("viewInvoke", "yes")
    }

    private fun getAllServices() = viewModelScope.launch {

        Log.d("room", "inside viewmodel")
        repository.getServicesFromNetwork()
        _services.postValue(repository.dataFromRepo.value)

    }
}