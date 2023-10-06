package com.maid.cleanhires.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maid.cleanhires.data.models.Services
import com.maid.cleanhires.repositories.ServiceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class ServiceViewModel(
    private val repository: ServiceRepository
) : ViewModel() {

    private val _services = MutableLiveData<List<Services>>()
    val services: LiveData<List<Services>> = _services

    init {
        getAllServices()
        Log.d("viewInvoke", "yes")
    }

    private fun getAllServices() = viewModelScope.launch {
        val response = repository.getServicesFromNetwork()
        _services.postValue(handleResponse(response))
//            handleResponse(response)
    }

    private fun handleResponse(response: Response<List<Services>>): List<Services> {
        if (response.isSuccessful) {
            response.body()?.let {
                return response.body()!!
            }
        }
        return listOf()
    }

//    fun getData(): List<Services> {
//        services?.let {
//            return services
//        }
//        return listOf()
//    }
}