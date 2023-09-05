package com.test.hiltapplication.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApp : Application(){

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object{
        var instance: HiltApp? = null
    }
}
