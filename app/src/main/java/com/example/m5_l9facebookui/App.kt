package com.example.m5_l9facebookui

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        lateinit var instance: Application
    }
}