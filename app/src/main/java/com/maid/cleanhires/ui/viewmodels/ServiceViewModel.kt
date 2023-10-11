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

//        if(NetworkConnection().isOnline(Application().baseContext)){
//            val response = repository.getServicesFromNetwork()
//            _services.postValue(response.body())
//            handleResponse(response)
//        }else{
//            val response = repository.getServiceFromDB()
//            _services.postValue(response.value)
//        }
        Log.d("room", "inside viewmodel")
        repository.getServicesFromNetwork()
        _services.postValue(repository.dataFromRepo.value)

    }

//    fun getData(): List<Services> {
//        services?.let {
//            return services
//        }
//        return listOf()
//    }

    private fun handleResponse(response: Response<List<Services>>) {
        if (response.isSuccessful) {
            response.body()?.let { it ->
                it.listIterator().forEach { services ->
                    Log.d("response", services.toString())
                    saveServices(services)
                }
            }
        }
    }

    private fun saveServices(services: Services) = viewModelScope.launch {
        repository.insertData(services)
    }

//    fun getServicesFromDB() = repository.getServiceFromDB()
}