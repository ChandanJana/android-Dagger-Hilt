package com.test.hiltapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.test.hiltapplication.application.HiltApp

/**
 * Created by Chandan Jana on 21-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */
object Utils {

    fun hasInternetConnection(): Boolean {
        if (HiltApp.instance == null)
            return false
        val connectivityManager = HiltApp.instance?.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}