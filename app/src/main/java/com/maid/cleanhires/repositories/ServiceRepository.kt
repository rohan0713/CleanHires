package com.maid.cleanhires.repositories

import ServiceDatabase
import com.maid.cleanhires.data.models.Services
import com.maid.cleanhires.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ServiceRepository(
//    private val db : ServiceDatabase
) {

    suspend fun getServicesFromNetwork() =
        withContext(Dispatchers.IO) {
            RetrofitClient.api.getServices()
        }

//    suspend fun insertData(services : Services) = db.getDao().upsert(services)
//
//    fun getServiceFromDB() = db.getDao().getServices()
}