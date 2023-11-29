package com.maid.cleanhires.network

import com.maid.cleanhires.data.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ProfileApi {

    @GET("authenticate")
    suspend fun login(@Query("email")email : String,
              @Query("password")password : String) : Response<User>

    @POST("create")
    suspend fun createAccount(@Query("email")email: String, @Query("password")password: String) : Response<User>

    @PUT("update")
    suspend fun updatePassword(
        @Query("email")email: String, @Query("password")password: String) : Response<User>
}