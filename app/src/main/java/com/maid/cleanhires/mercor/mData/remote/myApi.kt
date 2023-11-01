package com.maid.cleanhires.mercor.mData.remote

import com.maid.cleanhires.mercor.mData.models.ApiResponse
import com.maid.cleanhires.mercor.mData.models.ResultsItem
import retrofit2.Response
import retrofit2.http.GET

interface myApi {

    @GET("character")
    suspend fun getResults() : Response<ApiResponse>
}