package com.maid.cleanhires.network

import com.maid.cleanhires.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        private val retrofitClient by lazy {

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .build()

            Retrofit.Builder()
                .baseUrl(Constants.url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        private val userRetrofit by lazy {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .build()

            Retrofit.Builder()
                .baseUrl(Constants.profileURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api: Api by lazy {
            retrofitClient.create(Api::class.java)
        }

        val userApi: ProfileApi by lazy {
            userRetrofit.create(ProfileApi::class.java)
        }
    }
}