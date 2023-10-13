package com.maid.cleanhires.main

import android.app.Application
import com.maid.cleanhires.data.local.room.ServiceDatabase
import com.maid.cleanhires.repositories.ServiceRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

//    lateinit var repository: ServiceRepository
//
//    override fun onCreate() {
//        super.onCreate()
//
//        repository = ServiceRepository(ServiceDatabase(applicationContext), applicationContext)
//    }
}