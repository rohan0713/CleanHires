package com.maid.cleanhires.mercor.mData.remote

import com.maid.cleanhires.mercor.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        val retro by lazy {

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okhttpclient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

            Retrofit.Builder().baseUrl(Constants.url).client(okhttpclient).addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api by lazy {
            retro.create(myApi::class.java)
        }
    }
}