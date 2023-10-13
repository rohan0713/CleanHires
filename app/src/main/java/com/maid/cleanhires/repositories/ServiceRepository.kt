package com.maid.cleanhires.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.maid.cleanhires.data.local.room.ServiceDatabase
import com.maid.cleanhires.data.models.Services
import com.maid.cleanhires.network.RetrofitClient
import com.maid.cleanhires.utils.NetworkConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ServiceRepository @Inject constructor(
    private val db: ServiceDatabase,
    private val applicationContext: Context
) {

    private val _dataFromRepo = MutableLiveData<List<Services>>()
    val dataFromRepo: LiveData<List<Services>>
        get() = _dataFromRepo

    suspend fun getServicesFromNetwork() {

        Log.d("room", "network check")
        if (NetworkConnection().isOnline(context = applicationContext)) {
            Log.d("room", "inside network")
            withContext(Dispatchers.IO) {
                val response = RetrofitClient.api.getServices()
                if (response.body() != null) {
                    handleResponse(response)
                }
            }
        } else {
            withContext(Dispatchers.IO) {
                Log.d("room", "inside no network")
                val response = getServiceFromDB()
                Log.d("room", response.toString())
                _dataFromRepo.postValue(getServiceFromDB())
            }
        }
    }

    private suspend fun handleResponse(response: Response<List<Services>>) {
        if (response.isSuccessful) {
            response.body()?.let { it ->
                _dataFromRepo.postValue(it)
                it.listIterator().forEach { services ->
//                    Log.d("response", services.toString())
                    insertData(services)
                }
            }
        }
    }


    suspend fun insertData(services: Services) = db.getDao().upsert(services)

    private fun getServiceFromDB() = db.getDao().getServices()

}