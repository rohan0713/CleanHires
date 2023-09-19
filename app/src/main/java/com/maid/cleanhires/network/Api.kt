package com.maid.cleanhires.network

import com.maid.cleanhires.data.models.Services
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("services")
    suspend fun getServices() : Response<List<Services>>
}